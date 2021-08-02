package by.slavintodron.hw18.dataAnalyzer

import by.slavintodron.hw18.R
import by.slavintodron.hw18.regions.RegionEnum

class UtilsAnalyzer {
    fun getRegionNameStringID(regionEnum: RegionEnum): Int {
        return when (regionEnum) {
            RegionEnum.MINSK -> (R.string.region_minsk)
            RegionEnum.BREST -> (R.string.region_brest)
            RegionEnum.GRODNO -> (R.string.region_grodno)
        }
    }

}