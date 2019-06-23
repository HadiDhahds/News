package com.hadidhahds.news.data

import com.google.gson.annotations.SerializedName

class NewsObject {
    @SerializedName("articles")
    val list = ArrayList<NewsData>()
}