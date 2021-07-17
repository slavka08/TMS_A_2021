package by.slavintodron.quiz

import android.content.Context

class Quiz(private val context: Context) {

    private val mapQA: HashMap<String, String> = hashMapOf<String, String>(
        context.getString(R.string.q1) to context.getString(R.string.qa1),
        context.getString(R.string.q2) to context.getString(R.string.qa2),
        context.getString(R.string.q3) to context.getString(R.string.qa3),
        context.getString(R.string.q4) to context.getString(R.string.qa4),
    )
    private var numQuestion = 0

    fun nextQuestion(): String {
        return when (numQuestion++) {
            0 -> context.getString(R.string.q1)
            1 -> context.getString(R.string.q2)
            2 -> context.getString(R.string.q3)
            3 -> context.getString(R.string.q4)
            else -> context.getString(R.string.gameOver)
        }
    }
    fun checkAnswer (answer: String, question:String):Boolean{
       return (mapQA[question].equals(answer,true))
    }

    fun getAnswer (question:String): String? {
        return (mapQA[question])
    }

}