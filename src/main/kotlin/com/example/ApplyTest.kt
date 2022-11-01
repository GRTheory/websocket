package com.example

inline fun <T> List<T>.will(block: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    this.filter(block).forEach {
        result += it
    }
    return result
}

infix fun Int.mod(a: Int): Boolean {
    return this % a == 0
}

fun main() {
    val result = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    result[4, 0]
    println()
}

operator fun <T> List<T>.get(i: Int, j: Int): List<T> {
    if (i > j || i > size || j > size) {
        return listOf()
    }
    return this.subList(i, j)
}

//fun main() {
//    val list = listOf(1, 2, 3, 4, 5, 6).will {
//        it mod 2
//    }
//    println(list)
//
//    val result = TestA()
//    val result2 = TestA()
//    result += result2
//    println("${result.age} ${result.size}")
////    result += false
//}


class TestA {
    var age: Int = 14
    var size: Int = 15
}

operator fun TestA.plusAssign(b: TestA) {
    this.age += b.age
    this.size += b.size
}

//fun main() = "My cute pet is immortal".split(" ").filter {
//    it == it.lowercase()
//}.map {
//
//}.forEach {
//    println(it)
//}

//fun main() = runBlocking {
//    val mutex = Mutex()
//    var i = 0
//    for (d in 0..20000) {
//        launch {
//            i++
//        }
//    }
//    delay(1000)
//    coroutineContext.cancelChildren()
//    println(i)
//}

//fun main() = runBlocking {
//    val channel1 = Channel<Int>()
//    val channel2 = Channel<Int>()
//
//
//}

//@OptIn(ObsoleteCoroutinesApi::class)
//fun main() = runBlocking<Unit> {
//    val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0) // create ticker channel
//    var nextElement = withTimeoutOrNull(1) {
//        tickerChannel.receive()
//    }
//    println("Initial element is available immediately: $nextElement") // no initial delay
//
//    nextElement = withTimeoutOrNull(50) {
//        tickerChannel.receive()
//    }
//    println("Next element is not ready in 50 ms: $nextElement")
//
//    nextElement = withTimeoutOrNull(60) {
//        tickerChannel.receive()
//    }
//    println("Next element is ready in 100 ms: $nextElement")
//
//     Emulate large consumption delays
//    println("Consumer pauses for 150ms")
//    delay(150)
//     Next element is available immediately
//    nextElement = withTimeoutOrNull(1) {
//        tickerChannel.receive()
//    }
//    println("Next element is available immediately after large consumer delay: $nextElement")
//     Note that the pause between `receive` calls is taken into account and next element arrives faster
//    nextElement = withTimeoutOrNull(60) {
//        tickerChannel.receive()
//    }
//    println("Next element is ready in 50ms after consumer pause in 150ms: $nextElement")
//
//    tickerChannel.cancel() // indicate that no more elements are needed
//}

//data class Ball(var hits: Int)
//
//fun main() = runBlocking {
//    val table = Channel<Ball>() // a shared table
//    launch { player("ping", table) }
//    launch { player("Pong", table) }
//    table.send(Ball(0)) // serve the ball
//    delay(1000) // delay 1 second
//    coroutineContext.cancelChildren() // game over, cancel them
//}
//
//suspend fun player(name: String, table: Channel<Ball>) {
//    for (ball in table) {
//        ball.hits++
//        println("$name $ball")
//        delay(300)
//        table.send(ball)
//    }
//}

//fun main() = runBlocking {
//    val channel = Channel<Int>(4)
//    val sender = launch {
//        repeat(10) {
//            println("Sending $it")
//            channel.send(it)
//        }
//    }
//    delay(1000)
//    sender.cancel() // cancel sender coroutine
//}

//fun main() = runBlocking {
//    val channel = Channel<String>()
//    launch { sendString(channel, "foo", 200L) }
//    launch { sendString(channel, "BAR!", 500L) }
//    repeat(6) { // receive first six
//        println(channel.receive())
//    }
//    coroutineContext.cancelChildren() // cancel all children to let main finish
//}
//
//suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
//    while (true) {
//        delay(time)
//        channel.send(s)
//    }
//}


//fun main() = runBlocking<Unit> {
//    val producer = produceNumbers()
//    repeat(5) {
//        launchProcessor(it, producer)
//    }
//    delay(950)
//    producer.cancel()
//}
//
//@OptIn(ExperimentalCoroutinesApi::class)
//fun CoroutineScope.produceNumbers() = produce<Int> {
//    var x = 1
//    while (true) {
//        send(x++)
//        delay(100)
//    }
//}
//
//fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
//    for (msg in channel) {
//        println("Processor #$id received $msg")
//    }
//}

//fun fail(message: String): Nothing {
//    throw IllegalArgumentException(message)
//}
//
//class Person(var name: String, var age: Int) {
//    fun getMessage(): String {
//        return "the name is $name and age is $age"
//    }
//}
//
//fun main() {
//}

//fun main() {
//    loop@ for (i in 1..100) {
//        for (j in 1..100) {
//            if (i < j) break@loop
//        }
//    }
//}

//fun main() {
//    val k = "String is name".toCharArray()
//    println(k[0])
//    val d = 78
//    val r = d.toByte()
//    println()
//    val instruction = "683412400802000009008E09A25571233E75"
//    val regex = Regex("[A-Za-z1-9]{2}")
//    val result = instruction.split(regex = regex)
//    println(result)

//    val length = instruction.length
//    var i = 0
//    while (i < length) {
//        println("0x${instruction[i:i+2]},")
//    }
//}


//fun String.send() {
//    println(this)
//}

//fun main() {
//    var str: String? = null
//    str?.send() ?: run {
//        println("is null")
//    }
//
//}

//fun main() {
//    val str = "6834124059020000010041443131315430303030314443303735474f5030314d543032434e41465831302e312e33ffffff3839383630315959385353585858585858585850303132333435363738393031323334040100007f3132333435363738ffff46006834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f0166834124008020000090031a3fa778165b6d7f016683412"
//    println(str.length)
//    println(str.length/2)
//}

//fun main() = runBlocking {
//    launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello")
//}

//class KLazilySingleton private constructor() : java.io.Serializable {
//    fun doSomething() {
//        println("do some thing")
//    }
//
//    companion object {
//        private var mInstance: KLazilySingleton? = null
//            get() {
//                return field ?: KLazilySingleton()
//            }
//
//        @JvmStatic
//        @Synchronized
//        fun getInstance(): KLazilySingleton {
//            return requireNotNull(mInstance)
//        }
//    }
//
//    private fun readResolve(): Any {
//        return KLazilySingleton.getInstance()
//    }
//
//}
//
//fun main() {
//    KLazilySingleton.getInstance().doSomething()
//}

//class Demo {
//    var cat: Boolean = false
//
//    val getName: (String?, Int) -> Unit by lazy {
//        fun(id: String?, age: Int) {
//            cat = id?.contains(age.toString()) == true
//        }
//    }
//
//}

//fun main() {
//    val myDemo = Demo()
//    println(myDemo.cat)
//
//    myDemo.getName("12345", 34)
//    println(myDemo.cat)
//
//    val s = myDemo::getName
//    myDemo.getName("23", 1)
//    println(myDemo.cat)
//}


//fun main() {
//    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
//
//    val newNumberList1 = numberList.slice(IntRange(3, 6))
//    print("slice by IntRage: ")
//    newNumberList1.forEach {
//        print("$it ")
//    }
//
//    println()
//
//    val newNumberList2 = numberList.slice(listOf(1, 3, 7))
//    print("slice by iterator index: ")
//    newNumberList2.forEach {
//        print("$it ")
//    }
//}

//fun main() {
//    val nameList = listOf("Kotlin", "Java", "Python", "JavaScript", "Scala", "C")
//    nameList.filter {
//        it.startsWith("K")
//    }.map {
//        "$it is a very good language"
//    }.forEach {
//        println(it)
//    }
//}

//typealias NumPrint = (Int) -> Unit
//
//val defaultNumPrint = fun(input: Int) {
//    println(input)
//}
//
//fun d(input: Int) {
//    println(input * 5)
//}
//
//fun set(age: Int, func: NumPrint): Int {
//    func(age)
//    return 0
//}
//
//fun main() {
//    val result = set(2, defaultNumPrint)
//    println(result)
//    val result2 = set(5, ::d)
//    println(result2)
//}

//fun main() {
//    val oddNum: NumPrint = {
//        if (it % 2 == 1) {
//            println(it)
//        }  else {
//            println("is not a odd num")
//        }
//    }
//
//    oddNum(2)
//}

//class Person(var name: String, var age: Int) {
//    fun printPerson() {
//        println("name is $name and age is $age")
//    }
//
//}
//
//infix fun Person.equal(other: Person): Boolean = this == other
//
//
//fun main() {
//    val person1 = Person("bob", 23)
//    val person2 = Person("harry", 24)
//    if (person1 equal person2) {
//
//    }
//}


////class ApplyTest(var name: String?, var age: Int) {
////    fun outputMessage() {
////        println("name is $name and age is $age")
////    }
////}
////
////fun ApplyTest.play(test: ApplyTest.() -> Unit): ApplyTest {
////    test()
////    return this
////}
////

//
//sealed class BooleanExt<out T>
////object Otherwise : BooleanExt<Any?>()
//object Otherwise : BooleanExt<Nothing>()
////object TransferData : BooleanExt()
//
//class TransferData<T>(val data: T) : BooleanExt<T>()
//
//
//inline fun <T> Boolean.yes(block: () -> T): BooleanExt<T> = when {
//    this -> {
//        TransferData(block.invoke())
//    }
//    else -> {
//        Otherwise
//    }
//}
//
//inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T = when (this) {
//    is Otherwise ->
//        block()
//    is TransferData ->
//        this.data
//}

//interface Caster {
//
//}
//
//open class Animal {
//
//}
//
//open class Person : Animal() {
//
//}
//
//open class Woman : Person() {
//
//}
//
//class Creature<out T> {
//}
//
//fun main() {
//    val creature: Creature<Person> = Creature<Woman>()
//}
//
//class A<in T> {
//    fun doAction(t: T) {
//
//    }
//
//}

//fun main() {
//    val intA = A<Int>()
//    val anyA = A<Any>()
//    doSomething(intA)
//    doSomething(anyA)
//    var any: Number
//    any = 3.7
//    doSomething2(any)
//
//}

fun doSomething2(a: Number?) {

}


//fun main() {
//    val numberList: List<Int> = listOf(1, 2, 3)
//    val result = (numberList.size == 3).yes {
//        "true"
//    }.otherwise {
//        "false"
//    }
//
//    println(result)
//}

//fun main() {
//    val stringList: List<String> = listOf("a", "b", "c", "d")
//    val intList: List<Int> = listOf(1, 2, 3, 4)
//    printList(stringList)
//    printList(intList)
//}
//
//fun printList(list: List<Any>) {
//    list.forEach {
//        println(it)
//    }
//}

//fun main() {
//    val applyTest = ApplyTest("Lisi", 23).apply {
//        name = "Test"
//        age = 34
//    }
//
//    applyTest.play {
//        outputMessage()
//    }
////    val isInvoked = ApplyTest("Lisi", 23).run {
////        outputMessage()
////        true
////    }
////    println(isInvoked)
//}
