package by.slavintodron.hw15.furniture

class Table(
    height: Int,
    width: Int,
    material: String,
    color: String,
    val numLegs: Int
): Furniture(height, width, material, color) {

}