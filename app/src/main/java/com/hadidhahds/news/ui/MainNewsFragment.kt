package com.hadidhahds.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.hadidhahds.news.R
import com.hadidhahds.news.adapter.NewsAdapter
import com.hadidhahds.news.data.RetrofitFactory
import kotlinx.android.synthetic.main.fragment_main_news.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainNewsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val getData = RetrofitFactory().fetchData()

        try {

        CoroutineScope(Dispatchers.IO).launch {
            val response = getData.getNews()
            withContext(Dispatchers.Main){
                try {

                    val manager : FragmentManager = activity?.supportFragmentManager!!

                    if (response.isSuccessful) {
                        mainProgressBar.visibility = View.GONE
                        newsRecyvlerView?.apply {
                            layoutManager = LinearLayoutManager(activity?.applicationContext)
                            adapter = NewsAdapter(manager, response.body()?.list!!)
                        }
                    }else toast("nope")


                }catch (e : Exception) {
                    print(e.message)
                }


            }
        }

        }catch (c : Exception){
            toast("slow internet")
        }
    }

    fun toast(msg : String){
        Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_LONG).show()
    }

}
