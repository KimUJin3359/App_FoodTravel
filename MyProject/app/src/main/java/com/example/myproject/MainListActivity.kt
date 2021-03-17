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
import kotlinx.android.synthetic.main.activity_list.findbtn
import kotlinx.android.synthetic.main.activity_list.pIdEdit
import kotlinx.android.synthetic.main.activity_list.recyclerView
import kotlinx.android.synthetic.main.activity_main_list.*
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
import java.lang.ref.WeakReference
import java.net.URL

class MainListActivity : AppCompatActivity() {
    lateinit var adapter: MyAdapter
    lateinit var r: String
    lateinit var m: String
    var itemList :MutableList<MyData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
        var intent = intent
        r = intent.extras?.getString("region").toString()
        m = intent.extras?.getString("menu").toString()
        init()
        if(m == "전체") {
            startXMLTask()
        }
        else {
            startAnotherHTMLTask()
        }
    }

    private fun startXMLTask() {
        val task = MyAsyncTask(this)
        val url = "http://data.gb.go.kr/opendata/service/rest/gbRestaurantInfo/getRecordList?serviceKey=bl48WR6oJnes8GoWjSRphSsjPUKutcChoH2149vDBAdcMk8JSQr89CU1i0kyKjfyzJznjAt9Quy8%2FKWCW6SFQg%3D%3D&area=" + r + "&"
        task.execute(URL(url))
    }

    private fun startAnotherHTMLTask(){
        val task = MyAsyncTask(this)
        val url = "http://data.gb.go.kr/opendata/service/rest/localProducts/getRecordList?ServiceKey=bl48WR6oJnes8GoWjSRphSsjPUKutcChoH2149vDBAdcMk8JSQr89CU1i0kyKjfyzJznjAt9Quy8%2FKWCW6SFQg%3D%3D"
        task.execute(URL(url))
    }

    private fun init() {
        progressBar.visibility=View.VISIBLE
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
                var url = "https://m.search.naver.com/search.naver?where=m&sm=mtb_jum&query=" + adapter.items[position].name + " " + r
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
                if(adapter.items[position].tel != ""){
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + adapter.items[position].tel))
                    Toast.makeText(applicationContext, "${adapter.items[position].name}에 전화합니다.", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                } else{
                    Toast.makeText(applicationContext, "등록된 전화번호가 없습니다.", Toast.LENGTH_SHORT).show()
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

    class MyAsyncTask(context: MainListActivity): AsyncTask<URL, Unit, Unit>(){
        val activityreference = WeakReference(context)

        override fun doInBackground(vararg params: URL?) {
            val activity = activityreference.get()
            if(activity?.m == "전체"){
                activity?.adapter?.items?.clear()

                val doc = Jsoup.connect(params[0].toString()).parser(Parser.htmlParser()).get()
                val names = doc.select("item")
                for(i in names){
                    activity?.adapter?.items?.add(
                        MyData(
                            i.select("title").text(),
                            i.select("address").text(),
                            i.select("menu").text(),
                            i.select("area").text(),
                            i.select("tel").text()
                        )
                    )
                    activity?.itemList?.add(
                        MyData(
                            i.select("title").text(),
                            i.select("address").text(),
                            i.select("menu").text(),
                            i.select("area").text(),
                            i.select("tel").text()
                        )
                    )
                }
            }
            else{
                activity?.adapter?.items?.clear()

                val doc = Jsoup.connect(params[0].toString()).parser(Parser.htmlParser()).get()
                val names = doc.select("item")

                for (i in names) {
                    if (i.select("address").text().contains(activity?.r.toString()) && !i.select("address").text().contains("상주")) {
                        var s: String

                        if (i.select("content").text().contains("한우")) {
                            s = "한우"
                        } else if (i.select("content").text().contains("사과")) {
                            s = "사과"
                        } else if (i.select("content").text().contains("벼") || i.select("content").text().contains("쌀")) {
                            s = "쌀"
                        } else if (i.select("content").text().contains("고등어")) {
                            s = "고등어"
                        } else if (i.select("content").text().contains("멸치")) {
                            s = "멸치"
                        } else if (i.select("content").text().contains("용수농원")) {
                            s = "배"
                        } else {
                            s = "noInfo"
                        }

                        if(activity?.m == s) {
                            activity?.adapter?.items?.add(
                                MyData(
                                    i.select("subject").text(),
                                    i.select("address").text(),
                                    s,
                                    activity?.r,
                                    i.select("telephone").text()
                                )
                            )
                            activity?.itemList?.add(
                                MyData(
                                    i.select("subject").text(),
                                    i.select("address").text(),
                                    s,
                                    activity?.r,
                                    i.select("telephone").text()
                                )
                            )
                        }
                    }
                }
            }
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            val activity = activityreference.get()
            if(activity == null || activity.isFinishing)
                return
            activity.adapter.notifyDataSetChanged()
            activity.progressBar.visibility=View.GONE
        }
    }
}
