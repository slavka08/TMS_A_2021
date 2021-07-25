package by.slavintodron.hw16

import kotlin.random.Random

/**
 *
 * 1.Если а – четное посчитать а*б, иначе а+б используя when
 *
 */
class Task1 {
}

fun main() {
    val randomB = Random.nextInt(100)
    println("Enter b number is: $randomB")
    print("Enter a number: ")
    var readText = readLine()
    while (readText?.toIntOrNull() == null) {
        println("Not Int value")

        print("Enter number: ")
        readText = readLine()
    }
    val numA = readText?.toInt()

    val result = when ((numA % 2) > 0) {
        true -> numA + randomB
        false -> numA * randomB
    }
    println ("Result: $result")
}