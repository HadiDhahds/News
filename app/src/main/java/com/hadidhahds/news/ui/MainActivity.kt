package com.hadidhahds.news.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.hadidhahds.news.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsFragment = MainNewsFragment()

        val manager : FragmentManager = supportFragmentManager
        manager.beginTransaction().add(R.id.mainContainer, newsFragment).commit()
    }
}
