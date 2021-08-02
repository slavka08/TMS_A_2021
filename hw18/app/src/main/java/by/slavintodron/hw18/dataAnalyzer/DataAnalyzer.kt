package by.slavintodron.hw18.dataAnalyzer

import by.slavintodron.hw18.products.ProductsEnum
import by.slavintodron.hw18.regions.Region
import by.slavintodron.hw18.regions.RegionEnum

class DataAnalyzer(private val regions: Map<RegionEnum, Region>) {

    private var winnerMap: MutableMap<ProductsEnum, RegionEnum> = mutableMapOf()

    fun getLeaderByProduct(product: ProductsEnum): Pair<RegionEnum, Region>? {
        return regions.toList().maxByOrNull { it.second.products[product]?.count ?: 0 }
    }

    fun getWinnerByProduct(product: ProductsEnum): RegionEnum? {
        return winnerMap[product]
    }

    fun addRegionProduct(regionName: RegionEnum, productName: ProductsEnum, count: Int) {
        val region = regions[regionName]
        val currentCount = region?.products?.get(productName)?.count
        val product = region?.products?.get(productName)


        if (currentCount != null) {
            product?.count = currentCount + count
            if (!winnerMap.containsKey(productName))
                if (currentCount + count >= 100) {
                    winnerMap[productName] = regionName
                }
        }

    }
}