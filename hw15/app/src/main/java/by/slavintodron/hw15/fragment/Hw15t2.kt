package by.slavintodron.hw15.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import by.slavintodron.hw15.cat.Cat
import by.slavintodron.hw15.databinding.FragmentHw15t2Binding
import by.slavintodron.hw15.furniture.Table
import by.slavintodron.hw15.interfaces.IMainAct

/**
 *
 *
 * Создать класс Cat. Кот имеет свойства имя, вес, цвет, рост, длина и
 * зависящее от веса, роста и длины свойство - сила.

Необходимо создать функцию, определяющую высоту прыжка кота, зависящую от
базовых параметров кота (вес, рост, высота).
Создать базовый класс Furniture (мебель), обладающий параметрами: высота,
цвет, материал, длина. На базе класса Furniture создать класс Table
(добавить параметр количество ножек). Реализовать взаимодействие двух объектов:
Cat и Table. Вывести получилось ли у кота запрыгнуть на него.
 *
 */
class Hw15t2 : Fragment() {
    private var _binding: FragmentHw15t2Binding? = null
    private val binding get() = _binding!!
    private var mListener: IMainAct? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHw15t2Binding.inflate(inflater, container, false)

        binding.apply {
            buttonCreate.setOnClickListener {
                val cat = Cat(
                    color = itextCatColor.text.toString(),
                    name = itextCatName.text.toString(),
                    weight = itextCatWeigth.text.toString().toInt(),
                    height = itextCatHeight.text.toString().toInt(),
                    width = itextCatWidth.text.toString().toInt()
                )

                val table = Table(
                    height = itextTableHeight.text.toString().toInt(),
                    width = itextTableWidth.text.toString().toInt(),
                    material = itextTableMaterial.toString(),
                    color = itextTableColor.text.toString(),
                    numLegs = itextTableNumLeg.text.toString().toInt()
                )

                textViewTask4Res.text = if (cat.canCatJumpOn(table)) {
                    "Cat can jump on Table" +"\ncat jump max height: "+cat.getJumpHeight().toString()
                } else {
                    "Cat can't jump on Table" +"\ncat jump max height: "+cat.getJumpHeight().toString()
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
        fun newInstance() =
            Hw15t2().apply {

            }
    }
}