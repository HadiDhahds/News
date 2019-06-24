package com.hadidhahds.news.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hadidhahds.news.R
import com.hadidhahds.news.data.NewsData
import kotlinx.android.synthetic.main.news_layout.view.*
class NewsAdapter(private val clickListener: (NewsData) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private val items = arrayListOf<NewsData>()

    fun addItems(newItems: ArrayList<NewsData>?) {
        if (newItems != null) {
            items.removeAll(newItems)
            items.addAll(newItems)
            notifyItemRangeInserted(0, newItems.size)
        }
    }

    fun updateItems(updatedItems: ArrayList<NewsData>) {
        val oldSize = items.size
        items.addAll(updatedItems)
        notifyItemRangeInserted(oldSize, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = NewsHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
    )

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(items[position])
    }


    inner class NewsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item = view
        @SuppressLint("SetTextI18n")
        fun bind(news: NewsData) {


            item.mainNewsName.text = news.title

            if (news.author != null) item.mainNewsAuther.text = "By : ${news.author}"
            else {
                item.mainNewsAuther.visibility = View.GONE
            }

            Glide.with(item).load(news.urlToImage).into(item.mainNewsImage)


            item.mainCardNews.setOnClickListener {
                clickListener(news)
            }
        }

    }
}