package com.example.myproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        init()
    }

    private fun init() {
        button.setOnClickListener {
            //인텐트 -> 메인
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        imageButton.setOnClickListener {
            //인텐트 -> 설명
            val i = Intent(this, ExpActivity::class.java)
            startActivity(i)
        }

        button2.setOnClickListener {
            val i = Intent(this, FavoriteActivity::class.java)
            startActivity(i)
        }
    }
}
