package com.example

// collection filter example
//fun main() {
//    val numberList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//    val newNumberList = numberList.filter { number ->
//        number % 2 == 0
//    }
//
//    newNumberList.forEach { print("$it  ") }
//}

fun main() {
    val numberList1 = listOf(23, 65, 14, 57, 99, 123, 26, 15, 88, 37, 56)
    val numberList2 = listOf(13, 55, 24, 67, 93, 137, 216, 115, 828, 317, 16)
    val numberList3 = listOf(20, 45, 19, 7, 9, 3, 26, 5, 38, 75, 46)

    val newNumberList = mutableListOf<Int>().apply {
        numberList1.filterTo(this) {
            it % 2 == 0
        }
        numberList2.filterTo(this) {
            it % 2 == 0
        }
        numberList3.filterTo(this) {
            it % 2 == 0
        }
    }
    print("从三个集合筛选出的偶数集合: ")
    newNumberList.forEach {
        print("$it  ")
    }
}