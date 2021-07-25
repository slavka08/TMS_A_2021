package by.slavintodron.hw15.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import by.slavintodron.hw15.databinding.FragmentHw15t1Binding
import by.slavintodron.hw15.interfaces.IMainAct
import java.util.*


class Hw15t1 : Fragment() {
    private var _binding: FragmentHw15t1Binding? = null
    private val binding get() = _binding!!
    private var mListener: IMainAct? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHw15t1Binding.inflate(inflater, container, false)
        binding.apply {
            /**
             *
             * Создать программу, которая будет проверять попало ли случайно выбранное из отрезка [5;155]
             * целое число в интервал (25;100) и сообщать результат на экран.
             *
             */
            buttonGenerateT1.setOnClickListener {
                val randNum = Random().nextInt(152) + 4
                binding.textViewNum.text = randNum.toString()
                binding.textViewResult.text = if (randNum in 26..99) {
                    "Попало"
                } else {
                    "Не попало"
                }
            }
            /**
             *
             * Посчитайте сумму всех чисел от 1 до 100
             *
             */
            buttonCalculateT2.setOnClickListener {
                var sum = 0
                for (i in 1..100) {
                    sum += i
                }
                textViewT2Result.text = sum.toString()
            }
            /**
             *
             * Для введённого пользователем с клавиатуры натурального числа
             * посчитайте сумму всех его цифр (заранее не известно сколько цифр будет в числе).
             *
             */
            buttonCalculateT3.setOnClickListener {

                if (textInputNumber.text.toString().toIntOrNull() != null) {
                    var number = textInputNumber.text.toString()
                    val toList = number.toList()
                    var sum = 0
                    toList.forEach { sum += it.digitToInt() }
                    textViewT3Result.text = sum.toString()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    remove()
                    mListener?.openMain()
                }
            }
        )

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is IMainAct) {
            context
        } else {
            throw RuntimeException(
                "$context must implement OnFragment2DataListener"
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = Hw15t1()
    }
}