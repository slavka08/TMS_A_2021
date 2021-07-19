package by.slavintodron.magicball.classes

import android.content.Context
import by.slavintodron.magicball.R
import kotlin.random.Random

class MagicBall(context: Context) {
    var answerList: List<String>

    init {
        val stringArray = context.resources.getStringArray(R.array.string_array_answers)
        answerList = stringArray.toList()
    }

    fun getRandomAnswer(): String {
        return answerList[Random.nextInt(0, answerList.size)]
    }
}