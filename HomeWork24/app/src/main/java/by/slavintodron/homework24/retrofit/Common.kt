package by.slavintodron.homework24.retrofit

import by.slavintodron.homework24.retrofit.interfaces.ApiRequest

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitServices: ApiRequest
        get() = RetrofitClient.getClient(BASE_URL).create(ApiRequest::class.java)
}