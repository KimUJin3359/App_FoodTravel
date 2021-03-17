package com.example.myproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_daegu.*

class DaeguActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daegu)
        init()
    }

    private fun init() {
        buggu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "북구")
            startActivity(i)
        }

        donggu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "동구")
            startActivity(i)
        }

        seogu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "서구")
            startActivity(i)
        }

        junggu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "중구")
            startActivity(i)
        }

        suseonggu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "수성구")
            startActivity(i)
        }

        dalseogu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "달서구")
            startActivity(i)
        }

        dalseonggun.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "달성군")
            startActivity(i)
        }

        namgu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "남구")
            startActivity(i)
        }
    }
}
