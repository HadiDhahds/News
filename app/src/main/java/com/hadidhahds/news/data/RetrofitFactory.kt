package com.hadidhahds.news.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val apiKey = "apiKey=3db16fde189e428b8fb3514d07e0b249"
    }

    fun fetchData() : NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsService::class.java)
    }

}