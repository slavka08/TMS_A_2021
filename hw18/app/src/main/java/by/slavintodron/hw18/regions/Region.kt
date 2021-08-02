package by.slavintodron.hw18.regions

import by.slavintodron.hw18.products.ProductFactory

class Region(val nameId: Int) {
    var products = ProductFactory().make()

}