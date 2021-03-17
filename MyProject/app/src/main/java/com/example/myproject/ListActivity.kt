package com.example.myproject

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_list.*
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import java.lang.ref.WeakReference
import java.net.URL

class ListActivity : AppCompatActivity() {
    lateinit var adapter: MyAdapter
    lateinit var r: String
    lateinit var m: String
    var itemList :MutableList<MyData> = mutableListOf()
    lateinit var rdb: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        var intent = intent
        r = intent.extras?.getString("region").toString()
        m = intent.extras?.getString("menu").toString()
        rdb = FirebaseDatabase.getInstance().getReference("Products/restaurants")
        val query = FirebaseDatabase.getInstance().reference.child("Products/restaurants").limitToLast(50)
        val option = FirebaseRecyclerOptions.Builder<Product>().setQuery(query, Product::class.java).build()

        init()
        startXMLTask()
    }

    private fun startXMLTask() {
        val task = MyAsyncTask(this)
        task.execute(URL("https://www.daegufood.go.kr/kor/xml/alley.html"))
    }

    private fun init() {
        progressBar2.visibility=View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        adapter = MyAdapter(ArrayList<MyData>(), r)
        adapter.itemClickListener = object :MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: MyData,
                position: Int
            ) {
                var url = "https://m.search.naver.com/search.naver?where=m&sm=mtb_jum&query="+adapter.items[position].name+" "+r
                val intent = Intent(ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
        adapter.itemClickListener2 = object :MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter.MyViewHolder,
                view: View,
                data: MyData,
                position: Int
            ) {
                if(adapter.items[position].tel == "" || adapter.items[position].tel == "--"){
                    Toast.makeText(applicationContext, "등록된 전화번호가 없습니다.", Toast.LENGTH_SHORT).show()
                } else{
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + adapter.items[position].tel))
                    Toast.makeText(applicationContext, "${adapter.items[position].name}에 전화합니다.", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }

            }
        }

        recyclerView.adapter = adapter

        findbtn.setOnClickListener {
            var text = pIdEdit.text.toString()
            adapter.items.clear()
            for(i in itemList){
                if(i.menu.contains(text)){
                    adapter.items.add(i)
                }
            }
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    class MyAsyncTask(context: ListActivity): AsyncTask<URL, Unit, Unit>(){
        val activityrefrence = WeakReference(context)

        override fun doInBackground(vararg params: URL?) {
            val activity = activityrefrence.get()
            activity?.adapter?.items?.clear()

            val doc = Jsoup.connect(params[0].toString()).parser(Parser.htmlParser()).get()
            val names = doc.select("row")
            for(i in names){
                if(i.select("bz_nm").text().contains('.')) {

                }
                else {
                    if (i.select("fd_cs").text().contains(activity?.r.toString())) {
                        if (activity?.m == "전체") {
                            activity?.adapter?.items?.add(
                                MyData(
                                    i.select("BZ_NM").text(),
                                    i.select("GNG_CS").text(),
                                    i.select("MNU").text(),
                                    i.select("fd_cs").text(),
                                    i.select("TLNO").text()
                                )
                            )
                            activity?.itemList?.add(
                                MyData(
                                    i.select("BZ_NM").text(),
                                    i.select("GNG_CS").text(),
                                    i.select("MNU").text(),
                                    i.select("fd_cs").text(),
                                    i.select("TLNO").text()
                                )
                            )
                        } else {
                            if (activity?.m == "김광석" || activity?.m == "봉리단" || activity?.m == "동촌유원지" || activity?.m == "동화사" || activity?.m == "2030골목" ||
                                activity?.m == "갓바위" || activity?.m == "수성못"
                            ) {
                                if (i.select("fd_cs").text().contains(activity?.m.toString())) {
                                    activity?.adapter?.items?.add(
                                        MyData(
                                            i.select("BZ_NM").text(),
                                            i.select("GNG_CS").text(),
                                            i.select("MNU").text(),
                                            i.select("fd_cs").text(),
                                            i.select("TLNO").text()
                                        )
                                    )
                                    activity?.itemList?.add(
                                        MyData(
                                            i.select("BZ_NM").text(),
                                            i.select("GNG_CS").text(),
                                            i.select("MNU").text(),
                                            i.select("fd_cs").text(),
                                            i.select("TLNO").text()
                                        )
                                    )
                                }
                            } else {
                                if (i.select("mnu").text().contains(activity?.m.toString())) {
                                    activity?.adapter?.items?.add(
                                        MyData(
                                            i.select("BZ_NM").text(),
                                            i.select("GNG_CS").text(),
                                            i.select("MNU").text(),
                                            i.select("fd_cs").text().substring(1, 3),
                                            i.select("TLNO").text()
                                        )
                                    )
                                    activity?.itemList?.add(
                                        MyData(
                                            i.select("BZ_NM").text(),
                                            i.select("GNG_CS").text(),
                                            i.select("MNU").text(),
                                            i.select("fd_cs").text().substring(1, 3),
                                            i.select("TLNO").text()
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            val activity = activityrefrence.get()
            if(activity == null || activity.isFinishing)
                return
            activity.adapter.notifyDataSetChanged()
            activity.progressBar2.visibility=View.GONE
        }
    }
}
