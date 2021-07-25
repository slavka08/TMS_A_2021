package by.slavintodron.hw15.cat

import by.slavintodron.hw15.furniture.Furniture
import by.slavintodron.hw15.furniture.IFurniture

class Cat(
    val name: String,
    private val weight: Int,
    val color: String,
    private val height: Int,
    private val width: Int
): ICat {
    val strength: Int = (height * 10) + (width * 10) - weight
    override fun getJumpHeight(): Int {
        return  ((height * height) + (width * 2)) / weight
    }

    fun canCatJumpOn(furniture: IFurniture):Boolean{
        return (furniture.getHeight()<getJumpHeight())
    }

}