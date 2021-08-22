package by.slavintodron.homework24.retrofit.interfaces

import by.slavintodron.homework24.entity2.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequest {
    @GET("marvel")
    fun getUsersList(): Call<MutableList<Movie>>
}