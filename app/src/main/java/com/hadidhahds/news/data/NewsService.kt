package com.hadidhahds.news.data

import com.hadidhahds.news.data.RetrofitFactory.Companion.apiKey
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("everything?domains=wsj.com&$apiKey")
    suspend fun getNews():Response<NewsObject>
}