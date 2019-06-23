package com.hadidhahds.news.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide

import com.hadidhahds.news.R
import com.hadidhahds.news.data.RetrofitFactory
import kotlinx.android.synthetic.main.fragment_news_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
class NewsDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val url = arguments?.getString("url")
        val title = arguments?.getString("title")
        val author = arguments?.getString("author")
        val content = arguments?.getString("content")

        if (title != null) detailsName?.text = title
        else detailsName?.visibility = View.GONE
        if (author != null) detailsAuther?.text = author
        else {
            detailsAuther?.visibility = View.GONE
            byText?.visibility = View.GONE
        }
        if (content != null) detailsContent?.text = content
        else detailsContent?.visibility = View.GONE
        if (url != null) Glide.with(activity?.applicationContext!!).load(url).into(detailsImage)

    }
}
