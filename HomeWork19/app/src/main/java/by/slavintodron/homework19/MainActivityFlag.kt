package by.slavintodron.homework19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivityFlag : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val flagLayoutId = intent.getIntExtra(FLAG, 0)
        if (flagLayoutId > 0) {
            setContentView(flagLayoutId)
        } else {
            setContentView(R.layout.activity_main_flag)
        }
    }
}