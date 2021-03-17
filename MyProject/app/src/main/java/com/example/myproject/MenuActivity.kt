package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    lateinit var adapter: MyMenuAdapter
    lateinit var r: String
    var flag = 1
    var itemList : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        var intent = intent
        r = intent.extras?.getString("region").toString()
        init()
    }

    private fun init() {
        menuRecyclerView.layoutManager = GridLayoutManager(this, 3)
        datainput()
        adapter = MyMenuAdapter(itemList, r)
        adapter.itemClickListener = object :MyMenuAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyMenuAdapter.MyViewHolder,
                view: View,
                data: String,
                position: Int
            ) {
                if(flag == 1) {
                    val i = Intent(applicationContext, ListActivity::class.java)
                    i.putExtra("region", r)
                    i.putExtra("menu", adapter.items[position])
                    startActivity(i)
                }
                else{
                    val i = Intent(applicationContext, MainListActivity::class.java)
                    i.putExtra("region", r)
                    i.putExtra("menu", adapter.items[position])
                    startActivity(i)
                }
            }
        }
        menuRecyclerView.adapter = adapter
    }

    private fun datainput(){
        if(r == "중구"){
            flag =1
            if(!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");itemList.add("찜갈비");itemList.add("막창")
            itemList.add("2030골목");itemList.add("김광석");itemList.add("봉리단");
        }
        else if(r == "동구"){
            flag =1
            if(!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");itemList.add("보리밥");itemList.add("막창")
            itemList.add("곤드레");itemList.add("백숙");itemList.add("갓바위");
            itemList.add("동화사");itemList.add("동촌유원지");
        }
        else if(r == "서구"){
            flag =1
            if(!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");itemList.add("막창");itemList.add("아나고")
            itemList.add("곱창")
        }
        else if(r == "남구"){
            flag =1
            if(!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");itemList.add("막창");itemList.add("곱창");itemList.add("안동갈비");
        }
        else if(r == "북구"){
            flag =1
            if(!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");
        }
        else if(r == "수성구"){
            flag =1
            if(!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");itemList.add("수성못");itemList.add("막창");
        }
        else if(r == "달서구"){
            flag =1
            if(!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");itemList.add("백숙")
        }
        else if(r == "달성군") {
            flag =1
            if (!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");itemList.add("염소");itemList.add("백숙");
            itemList.add("찐빵");
        }
        else if(r == "영주시" || r == "봉화군" || r == "예천군" || r == "안동시" ||
            r == "영양군" || r == "영덕군" || r == "청송군" || r == "상주시" ||
            r == "김천시" || r == "구미시" || r == "성주군" || r == "칠곡군" ||
            r == "고령군" ||r == "포항시" || r == "청도군" || r == "경주시") {
            flag = 0
            if (!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체");
        }
        else if(r == "경산시"){
            flag = 0
            if (!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체"); itemList.add("한우")
        }
        else if(r == "군위군" || r == "의성군"){
            flag = 0
            if (!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체"); itemList.add("사과")
        }
        else if(r == "영천시"){
            flag = 0
            if (!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체"); itemList.add("배")
        }
        else if(r == "문경시"){
            flag = 0
            if (!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체"); itemList.add("사과");itemList.add("고등어");
            itemList.add("쌀")
        }
        else if(r == "울진군"){
            flag = 0
            if (!itemList.isEmpty())
                itemList.clear()
            itemList.add("전체"); itemList.add("멸치")
        }
    }
}
