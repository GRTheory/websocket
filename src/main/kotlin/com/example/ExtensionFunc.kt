package com.example

import java.io.File
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


data class Message(val msg: String)

@ExperimentalContracts
fun handleMessage(message: Any?) {
    if (isInstanceOf(message)) {
        println(message.msg)
    }
}

@ExperimentalContracts
fun isInstanceOf(message: Any?): Boolean {
    contract {
        returns(true) implies (message is Message)
    }
    return message is Message
}

//inline fun <reified T> Any.isInstanceOf(): Boolean = this is T

fun main() {

}

// Normal approach
//fun makeDir(path: String): File {
//    val result = File(path)
//    result.mkdirs()
//    return result
//}

// Improved approach
fun makeDir(path: String) = path.let { File(it) }.also { it.mkdirs() }

//fun main() {
//    val original = "abc"
//
//    original.let {
//        println("The original String is $it")
//        it.reversed()
//    }.let {
//        println("The reverse String is $it")
//        it.length
//    }.let {
//        println("The length of the String is $it")
//    }
//
//    original.also {
//        println("The original String is $it")
//    }.also {
//        println("The reverse String is ${it.reversed()}")
//    }.also {
//        println("The length of the String is ${it.length}")
//    }
//}

//fun main() {
//    var str: String
//    "String".run { }
//    "String".let { customized ->
//        str = customized
//        println(customized)
//    }
//
//    "String".let {
//        str = it
//        println(it)
//    }
//}