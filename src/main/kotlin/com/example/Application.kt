package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.network.tls.certificates.*
import io.ktor.server.application.*
import org.slf4j.LoggerFactory
import java.io.File

//fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
//        configureSockets()
//        configureRouting()
//    }.start(wait = true)
//}

// generate a certificate
fun main() {
    val keyStoreFile = File("build/keystore.jks")
    val keystore = generateCertificate(
        file = keyStoreFile, keyAlias = "sampleAlias", keyPassword = "foobar", jksPassword = "foobar"
    )

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            port = 8080
        }
        sslConnector(keyStore = keystore,
            keyAlias = "sampleAlias",
            keyStorePassword = { "foobar".toCharArray() },
            privateKeyPassword = { "foobar".toCharArray() }) {
            port = 8443
            keyStorePath = keyStoreFile
        }
        module(Application::configureRouting)
        module(Application::configureSockets)
    }

    embeddedServer(Netty, environment).start(wait = true)
}