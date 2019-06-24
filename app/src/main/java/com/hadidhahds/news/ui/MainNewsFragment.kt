package com.hadidhahds.news.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hadidhahds.news.R
import com.hadidhahds.news.adapter.NewsAdapter
import com.hadidhahds.news.viewModel.NewsList
import kotlinx.android.synthetic.main.fragment_main_news.*

class MainNewsFragment : Fragment() {

    private  val newsList = NewsList()

    private val newsAdapter = NewsAdapter {
        val newsFragment = NewsDetailsFragment()
        val bundle = Bundle()
        newsFragment.arguments = bundle
        bundle.putString("title", it.title)
        bundle.putString("author", it.author)
        bundle.putString("content", it.description)
        bundle.putString("url", it.urlToImage)
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.mainContainer, newsFragment)
            ?.addToBackStack(null)?.commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

    }

    private fun getData(){

        newsList.stats.observe(this, Observer {status ->
            if (status == "success"){
                newsList.newsList.observe(this, Observer {
                    mainProgressBar.visibility = View.GONE
                    newsRecyvlerView?.apply {
                        layoutManager = LinearLayoutManager(activity?.applicationContext)
                        adapter = newsAdapter
                    }
                    newsAdapter.addItems(it)
                })
            } else if (status == "failed"){
                toast("failed to get Data")
            }
        })
       }

    fun toast(msg: String) {
        Toast.makeText(activity?.applicationContext, msg, Toast.LENGTH_LONG).show()
    }

}
