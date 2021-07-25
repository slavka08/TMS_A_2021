package by.slavintodron.hw15.furniture

open class Furniture(
    private val height: Int,
    private val width: Int,
    private val material: String,
    private val color: String
) : IFurniture {
    override fun getHeight(): Int {
        return height
    }
}