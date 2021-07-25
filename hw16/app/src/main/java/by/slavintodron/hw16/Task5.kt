package by.slavintodron.hw16

/**
 *
 * 5.Найти сумму четных чисел и их количество в диапазоне от 1 до 99
 */
class Task5 {
}

fun main() {
    var sum = 0
    var counter = 0
    for (x in 2..99 step (2)) {
        sum += x
        counter++
        println(x)
    }
    println("Сумма четных:$sum элементов:$counter")
}