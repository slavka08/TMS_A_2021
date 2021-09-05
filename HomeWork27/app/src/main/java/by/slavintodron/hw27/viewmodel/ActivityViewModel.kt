package by.slavintodron.hw27.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.slavintodron.hw27.api.WeatherApi
import by.slavintodron.hw27.entity.WeatherEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityViewModel : ViewModel() {
    private val _weather = MutableLiveData<WeatherEntity>()
    val weather get() = _weather
    fun getWeather(city: String) {

        WeatherApi.create().getWeather(city).enqueue(object : Callback<WeatherEntity> {
            override fun onResponse(
                call: Call<WeatherEntity>,
                response: Response<WeatherEntity>
            ) {
                _weather.value = response.body()
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
            }
        })
    }

}