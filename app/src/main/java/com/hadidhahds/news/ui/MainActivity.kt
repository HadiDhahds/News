package com.hadidhahds.news.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hadidhahds.news.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, MainNewsFragment()).commit()
    }
}
