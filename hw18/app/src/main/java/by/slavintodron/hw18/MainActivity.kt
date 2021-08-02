package by.slavintodron.hw18

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.slavintodron.hw18.dataAnalyzer.DataAnalyzer
import by.slavintodron.hw18.dataAnalyzer.UtilsAnalyzer
import by.slavintodron.hw18.databinding.ActivityMainBinding
import by.slavintodron.hw18.products.ProductsEnum
import by.slavintodron.hw18.regions.RegionEnum
import by.slavintodron.hw18.regions.RegionsFactory
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var jobMinsk: Job? = null
    private var jobBrest: Job? = null
    private var jobGrodno: Job? = null

    private lateinit var binding: ActivityMainBinding

    private var analyzer: DataAnalyzer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        analyzer = DataAnalyzer(RegionsFactory().make())

        createCoroutines()

    }

    private fun updateRegionData() {
        printLeader(ProductsEnum.POTATO)
        printLeader(ProductsEnum.CABBAGE)
        printLeader(ProductsEnum.BEETROOT)
        printWinner(ProductsEnum.POTATO)
        printWinner(ProductsEnum.CABBAGE)
        printWinner(ProductsEnum.BEETROOT)
    }

    private fun printLeader(productsEnum: ProductsEnum) {
        val leaderRegionByProduct = analyzer?.getLeaderByProduct(productsEnum)
        val leaderRegionName = when (leaderRegionByProduct?.first) {
            RegionEnum.MINSK -> getString(R.string.region_minsk)
            RegionEnum.BREST -> getString(R.string.region_brest)
            RegionEnum.GRODNO -> getString(R.string.region_grodno)
            else -> ""
        }

        val count = leaderRegionByProduct?.second?.products?.get(productsEnum)?.count

        when (productsEnum) {
            ProductsEnum.POTATO -> binding.textViewPotatoResult.text =
                "$leaderRegionName  $count ${getString(R.string.weigth_tons)}"
            ProductsEnum.BEETROOT -> binding.textViewBeetrootResult.text =
                "$leaderRegionName $count ${getString(R.string.weigth_tons)}"
            ProductsEnum.CABBAGE -> binding.textViewCabbageResult.text =
                "$leaderRegionName $count ${getString(R.string.weigth_tons)}"
        }

    }

    private fun printWinner(productsEnum: ProductsEnum) {
        val regionName: String? = analyzer?.getWinnerByProduct(productsEnum)?.let { regionEnum ->
            UtilsAnalyzer().getRegionNameStringID(
                regionEnum
            )
        }?.let { getString(it) }

        if (regionName != null)
            when (productsEnum) {
                ProductsEnum.POTATO -> binding.textViewPotatoWin.text =
                    "$regionName"
                ProductsEnum.BEETROOT -> binding.textViewBeetrootWin.text =
                    "$regionName"
                ProductsEnum.CABBAGE -> binding.textViewCabbageWin.text =
                    "$regionName"
            }
    }

    private fun createCoroutines() {

        fun getRandomProduct(): ProductsEnum {
            return ProductsEnum.values()[Random.nextInt(ProductsEnum.values().size)]
        }

        jobMinsk = GlobalScope.launch {
            while (isActive) {
                withContext(Dispatchers.Main) {
                    delay(Random.nextLong(MinDelay, MaxDelay))
                    analyzer?.addRegionProduct(
                        RegionEnum.MINSK,
                        getRandomProduct(),
                        Random.nextInt(MaxProductivity)
                    )
                    updateRegionData()
                }
            }
        }

        jobBrest = GlobalScope.launch {
            while (isActive) {
                withContext(Dispatchers.Main) {
                    delay(Random.nextLong(MinDelay, MaxDelay))
                    analyzer?.addRegionProduct(
                        RegionEnum.BREST,
                        getRandomProduct(),
                        Random.nextInt(MaxProductivity)
                    )
                    updateRegionData()
                }

            }
        }

        jobGrodno = GlobalScope.launch {
            while (isActive) {
                withContext(Dispatchers.Main) {
                    delay(Random.nextLong(MinDelay, MaxDelay))
                    analyzer?.addRegionProduct(
                        RegionEnum.GRODNO,
                        getRandomProduct(),
                        Random.nextInt(MaxProductivity)
                    )
                    updateRegionData()
                }

            }
        }
    }

    companion object {
        const val MinDelay: Long = 500
        const val MaxDelay: Long = 3000
        const val MaxProductivity = 50
    }

}