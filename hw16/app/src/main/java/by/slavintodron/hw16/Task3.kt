package by.slavintodron.hw16

class Task3 {
}

/**
 *
 * Написать программу определения оценки студента по его рейтингу, на основе следующих правил:
рейтинг Оценка
0-19 F
20-39 E
40-59 D
60-74 C
75-89 B
90-100 A
 *
 */
fun main() {
   println( convertGradeToTextGrade(inputValue()))
}

fun inputValue(): Int {
    print("Enter number rating: ")
    var readText = readLine()
    while (readText?.toIntOrNull() == null) {
        println("Not Int value")

        print("Enter number rating: ")
        readText = readLine()
    }
    return readText?.toInt()
}

fun convertGradeToTextGrade(value:Int): String {
    return when {
        value < 0 -> "wrong num"
        value < 20 -> "F"
        value < 40 -> "E"
        value < 60 -> "D"
        value < 75 -> "C"
        value < 90 -> "B"
        value < 101 -> "A"
        else -> "wrong num"
    }
}