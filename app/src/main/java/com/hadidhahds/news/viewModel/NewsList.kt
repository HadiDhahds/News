package com.hadidhahds.news.viewModel

import android.app.usage.ConfigurationStats
import android.arch.lifecycle.MutableLiveData
import com.hadidhahds.news.data.NewsData
import com.hadidhahds.news.data.NewsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsList {

     var newsList = MutableLiveData<ArrayList<NewsData>>()
     var stats= MutableLiveData<String>()

    init {

        CoroutineScope(Dispatchers.IO).launch{
            val response = NewsService.invoke().getNews()
            withContext(Dispatchers.Main){

                if (response.isSuccessful) {
                    stats.value = "success"
                    newsList.value = response.body()?.list
                }else { stats.value = "failed"}
            }
        }

    }

}