package by.tms.homework34

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.tms.homework34.databinding.ActivityMainBinding
import android.R.attr.password
import io.reactivex.Observable


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailChangeObservable = binding.tvLogin.textChangeEvents(email)
        passwordChangeObservable = RxTextView.textChangeEvents(password)

// force-disable the button

// force-disable the button
        submitButton.setEnabled(false)

        Observable.combineLatest(
            emailChangeObservable, passwordChangeObservable
        ) { emailObservable, passwordObservable ->
            val emailCheck: Boolean = emailObservable.text().length() >= 3
            val passwordCheck: Boolean = passwordObservable.text().length() >= 3
            emailCheck && passwordCheck
        }.subscribe { aBoolean -> submitButton.setEnabled(aBoolean) }
    }
}