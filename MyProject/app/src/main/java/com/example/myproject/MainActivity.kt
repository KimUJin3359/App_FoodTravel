package com.example.myproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        daegu.setOnClickListener {
            val i = Intent(this, DaeguActivity::class.java)
            startActivity(i)
        }

        youngju.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "영주시")
            startActivity(i)
        }

        bonghwa.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "봉화군")
            startActivity(i)
        }

        uljin.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "울진군")
            startActivity(i)
        }

        munkyeong.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "문경시")
            startActivity(i)
        }

        yecheon.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "예천군")
            startActivity(i)
        }

        andong.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "안동시")
            startActivity(i)
        }

        youngyang.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "영양군")
            startActivity(i)
        }

        youngduck.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "영덕군")
            startActivity(i)
        }

        cheongsong.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "청송군")
            startActivity(i)
        }

        eiseong.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "의성군")
            startActivity(i)
        }

        sangju.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "상주시")
            startActivity(i)
        }

        kimcheon.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "김천시")
            startActivity(i)
        }

        gumi.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "구미시")
            startActivity(i)
        }

        gunwi.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "군위군")
            startActivity(i)
        }

        seongju.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "성주군")
            startActivity(i)
        }

        chilgok.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "칠곡군")
            startActivity(i)
        }

        goryeong.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "고령군")
            startActivity(i)
        }

        youngcheon.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "영천시")
            startActivity(i)
        }

        pohang.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "포항시")
            startActivity(i)
        }

        gyeongsan.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "경산시")
            startActivity(i)
        }

        cheongdo.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "청도군")
            startActivity(i)
        }

        gyeongju.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            i.putExtra("region", "경주시")
            startActivity(i)
        }
    }
}
