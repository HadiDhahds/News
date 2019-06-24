package com.hadidhahds.news.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsService {

    @GET("everything?domains=wsj.com&$apiKey")
    suspend fun getNews(): Response<NewsObject>

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        private const val apiKey = "apiKey=3db16fde189e428b8fb3514d07e0b249"

        operator fun invoke(): NewsService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsService::class.java)

    }


}