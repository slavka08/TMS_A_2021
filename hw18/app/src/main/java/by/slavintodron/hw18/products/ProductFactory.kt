package by.slavintodron.hw18.products

import by.slavintodron.hw18.R

class ProductFactory {
    fun make() = mutableMapOf<ProductsEnum, Product>(
        ProductsEnum.POTATO to Product(
            R.string.product_potato
        ),
        ProductsEnum.CABBAGE to Product(
            R.string.product_cabbage
        ),
        ProductsEnum.BEETROOT to Product(
            R.string.product_beetroot
        )
    )

}