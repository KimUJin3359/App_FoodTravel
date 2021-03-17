package com.example.myproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_favorite.*


class FavoriteActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyProductAdapter
    lateinit var rdb: DatabaseReference
    var findQuery = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        init()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        rdb = FirebaseDatabase.getInstance().getReference("Products/restaurants")
        val query = FirebaseDatabase.getInstance().reference.child("Products/restaurants").limitToLast(50)
        val option = FirebaseRecyclerOptions.Builder<Product>().setQuery(query, Product::class.java).build()
        adapter = MyProductAdapter(option)
        adapter.itemClickListener = object  :MyProductAdapter.OnItemClickListener{
            override fun OnItemClick(view: View, position: Int) {
                var url = "https://m.search.naver.com/search.naver?where=m&sm=mtb_jum&query="+adapter.getItem(position).name+" "+adapter.getItem(position).region
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
        adapter.itemClickListener2 = object : MyProductAdapter.OnItemClickListener{
            override fun OnItemClick(view: View, position: Int) {
                if(adapter.getItem(position).tel == "" || adapter.getItem(position).tel == "--"){
                    Toast.makeText(applicationContext, "등록된 전화번호가 없습니다.", Toast.LENGTH_SHORT).show()
                } else{
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + adapter.getItem(position).tel))
                    Toast.makeText(applicationContext, "${adapter.getItem(position).name}에 전화합니다.", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
            }
        }
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}
