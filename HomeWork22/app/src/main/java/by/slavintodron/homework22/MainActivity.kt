package by.slavintodron.homework22

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.homework22.databinding.ActivityMainBinding

const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.materialButtonLogIn.setOnClickListener {

            var checkParamsForLogIn = true
            /**
             * проверка логина (email)
             */
            if (binding.etLogin.text?.matches(emailPattern.toRegex()) == true) {
                binding.etLogin.error = null
            } else {
                checkParamsForLogIn = false
                binding.etLogin.error = getString(R.string.error_mail)
            }

            /**
             * проверка пароля на пусорту
             */
            if (binding.etPassword.text?.length!! > 0) {
                binding.etPassword.error = null
            } else {
                binding.etPassword.error = getString(R.string.error_pass)
                checkParamsForLogIn = false
            }

            if (checkParamsForLogIn) {
                binding.materialButtonLogIn.error = null
                if ((binding.etLogin.text.toString() == "dz@gmail.com") &&
                    (binding.etPassword.text.toString() == "1234"))
                {
                    Toast.makeText(this, getString(R.string.login_ok), Toast.LENGTH_SHORT).show()
                    binding.materialButtonLogIn.icon = getDrawable(R.drawable.ic_done_black_24dp)
                }
            }else {
                binding.materialButtonLogIn.icon = getDrawable(R.drawable.ic_login_black_24dp)
                binding.materialButtonLogIn.setError(
                    getString(R.string.error_login)
                )
            }
        }

        setContentView(binding.root)
    }
}