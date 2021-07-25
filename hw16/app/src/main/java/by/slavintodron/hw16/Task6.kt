package by.slavintodron.hw16

/**
 * .Написать функцию, которая возвращает факториал числа N (n! = 1*2*…*n-1*n;)
 */
class Task6 {
}
fun main(){
println(factorial(40))
}

fun factorial(n: Int): Int = if (n < 2) 1 else n * factorial(n - 1)