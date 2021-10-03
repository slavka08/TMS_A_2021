package by.tms.homework34

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.tms.homework34.databinding.ActivityMainBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val emailChangeObservable = RxTextView.textChangeEvents(binding.tvLogin)
        val passwordChangeObservable = RxTextView.textChangeEvents(binding.tvPassword)
        binding.button.isEnabled = false
        Observable.combineLatest(
            emailChangeObservable, passwordChangeObservable
        ) { emailObservable, passwordObservable ->
            val emailCheck: Boolean = emailObservable.text().length >= 1
            val passwordCheck: Boolean = passwordObservable.text().length >= 1
            emailCheck && passwordCheck
        }.subscribe { aBoolean -> binding.button.isEnabled = aBoolean }
    }
}