package by.slavintodron.homework21.mail

import java.util.*
import kotlin.random.Random

class MailBuilder {
    fun make(): Array<MailEntity> {
        val tmpList: MutableList<MailEntity> = mutableListOf()
        for (i in 0..10) {
            tmpList.add(
                MailEntity(
                    sender = random(12),
                    theme = random(20),
                    message = random(255),
                    date = Date(
                        Calendar.getInstance().time.time + Random.nextLong(100005000000L) - Random.nextLong(
                            100005000000L
                        )
                    )
                )
            )
        }
        return tmpList.toTypedArray()
    }

    private fun random(length: Int): String {
        val randomStringBuilder = StringBuilder()
        var tempChar: Char
        for (i in 0 until Random.nextInt(length)) {
            tempChar = ((Random.nextInt(26) + 96).toChar())
            randomStringBuilder.append(tempChar)
        }
        return randomStringBuilder.toString()
    }
}