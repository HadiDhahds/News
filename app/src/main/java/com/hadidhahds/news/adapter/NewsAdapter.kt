package com.hadidhahds.news.adapter

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hadidhahds.news.R
import com.hadidhahds.news.data.NewsData
import com.hadidhahds.news.ui.MainNewsFragment
import com.hadidhahds.news.ui.NewsDetailsFragment
import kotlinx.android.synthetic.main.news_layout.view.*

class NewsAdapter(val manager: FragmentManager, val list: ArrayList<NewsData>) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = NewsHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
    )

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(list[position])
     }

    inner class NewsHolder(view : View): RecyclerView.ViewHolder(view) {
        val item = view
        fun bind(news : NewsData){

            item.mainNewsName.text = news.title

            if (news.author != null) item.mainNewsAuther.text = news.author
            else {
                item.customByText.visibility = View.GONE
                item.mainNewsAuther.visibility = View.GONE
            }
            Glide.with(item).load(news.urlToImage).into(item.mainNewsImage)
            item.mainCardNews.setOnClickListener {
                val newsFragment = NewsDetailsFragment()
                val bundle = Bundle()
                newsFragment.arguments = bundle
                bundle.putString("title", news.title)
                bundle.putString("author", news.author)
                bundle.putString("content", news.description)
                bundle.putString("url", news.urlToImage)
                //val manager = (context as Activity()).supportFragmentManager.beginTransaction()

               // val manager : FragmentManager = fragment.
                manager.beginTransaction()
                    .replace(R.id.mainContainer, newsFragment)
                    .addToBackStack(null).commit()
            }
        }
    }

}