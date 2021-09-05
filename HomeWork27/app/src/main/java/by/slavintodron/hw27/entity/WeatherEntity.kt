package by.slavintodron.hw27.entity

data class WeatherEntity(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
)