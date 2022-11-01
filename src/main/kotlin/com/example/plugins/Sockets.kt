package com.example.plugins

import com.example.Connection
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.sync.Mutex
import java.time.Duration
import java.util.*
import kotlin.collections.set


data class Websocket<K>(val id: K, val conn: WebSocketSession)


class Server<K>(var hostname: String) {
    val mutex: Mutex = Mutex(false)
    val connections: MutableMap<K, Websocket<K>> = HashMap()
    var messageHandler: ((Websocket<K>, String) -> Unit)? = null
//    var pingHandler: ((Websocket<K>) -> Unit)? = null


    suspend fun write(sn: K, data: String) {
        mutex.lock(connections)
        val websocket = connections[sn]
        mutex.unlock(connections)
        websocket?.run {
            conn.send(data)
        }
    }

    operator fun set(k: K, session: Websocket<K>) {
        connections[k] = session
    }
}

//operator fun <K> Server<K>.set(k: K, session: DefaultWebSocketSession) {
//    connections[k] = session
//}

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    val myServer = Server<String>("hostname")
    myServer.messageHandler = fun(_: Websocket<String>, message: String) {
        println(message)
    }

    routing {
        webSocket("/ocpp/{client?}") {
            val snPassword = call.parameters["client"] ?: return@webSocket close(
                reason = CloseReason(
                    CloseReason.Codes.NORMAL, "Empty Id Parameter"
                )
            )
            // split the sn and password
            var password: String? = null
            val sn = snPassword.split(":").let {
                if (it.size > 1) {
                    password = it[1]
                }
                it[0]
            }

            myServer.connections[sn]?.let {
                myServer.mutex.lock(myServer.connections)
                it.conn.close(
                    reason = CloseReason(
                        CloseReason.Codes.NORMAL, "Empty Id Parameter"
                    )
                )
                myServer.connections.remove(sn)
                myServer.mutex.unlock(myServer.connections)
            }

            val websocket = Websocket(sn, this)
            myServer[sn] = websocket
            try {
                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val receivedText = frame.readText()
                            myServer.messageHandler?.let {
                                it(websocket, receivedText)
                            }
                            myServer.write(sn, receivedText)
                        }
                        else -> continue
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                println("connection is closed $sn + ${password ?: "no password"}")
            }
        }

        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket("/chat/{id?}") {
            val sn = call.parameters["id"] ?: return@webSocket close(
                reason = CloseReason(
                    CloseReason.Codes.NORMAL, "Empty Id Parameter"
                )
            )

            println("Adding user $sn!")
            val thisConnection = Connection(this)
            connections += thisConnection

            try {
                send("You are connected! There are ${connections.count()} users here.")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receiveText = frame.readText()
                    val textWithUsername = "[${thisConnection.name}]: $receiveText"
                    connections.forEach {
                        it.session.send(textWithUsername)
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                println("Removing $thisConnection!")
                connections -= thisConnection
            }
        }
    }
}
