package by.slavintodron.homework20

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val timerLiveData = MutableLiveData<Long>()
    fun startTimer( value: Long?) {
        if (value != null) {
            object : CountDownTimer(value * 1000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    timerLiveData.value = (millisUntilFinished / 1000)
                }

                override fun onFinish() {
                }

            }.start()
        }
    }
}