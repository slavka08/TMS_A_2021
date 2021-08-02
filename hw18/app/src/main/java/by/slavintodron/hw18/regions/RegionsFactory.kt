package by.slavintodron.hw18.regions

import android.content.res.Resources
import by.slavintodron.hw18.R

class RegionsFactory {
    fun make() = mapOf(
        RegionEnum.MINSK to Region((R.string.region_minsk)),
        RegionEnum.GRODNO to Region((R.string.region_grodno)),
        RegionEnum.BREST to Region((R.string.region_brest))
    )
}