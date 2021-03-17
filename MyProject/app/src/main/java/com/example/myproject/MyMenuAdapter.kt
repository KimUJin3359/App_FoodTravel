package com.example.myproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MyMenuAdapter(val items : MutableList<String>, val region: String) :
    RecyclerView.Adapter<MyMenuAdapter.MyViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view: View, data: String, position: Int)
    }

    var itemClickListener: OnItemClickListener ?= null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.imageButton)
        init{
            image.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.button_row, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(items[position] == "전체"){
            holder.image.setImageResource(R.drawable.all)
        }
        else if(items[position] == "아나고"){
            holder.image.setImageResource(R.drawable.anago)
        }
        else if(items[position] == "안동갈비"){
            holder.image.setImageResource(R.drawable.andonggalbi)
        }
        else if(items[position] == "백숙"){
            holder.image.setImageResource(R.drawable.baeksuk)
        }
        else if(items[position] == "봉리단"){
            holder.image.setImageResource(R.drawable.bongridan)
        }
        else if(items[position] == "보리밥"){
            holder.image.setImageResource(R.drawable.bori)
        }
        else if(items[position] == "동촌유원지"){
            holder.image.setImageResource(R.drawable.dongchon)
        }
        else if(items[position] == "동화사"){
            holder.image.setImageResource(R.drawable.donghwasa)
        }
        else if(items[position] == "2030골목"){
            holder.image.setImageResource(R.drawable.dongseongro)
        }
        else if(items[position] == "갓바위"){
            holder.image.setImageResource(R.drawable.gadbawi)
        }
        else if(items[position] == "곱창"){
            holder.image.setImageResource(R.drawable.gobchang)
        }
        else if(items[position] == "곤드레"){
            holder.image.setImageResource(R.drawable.gondre)
        }
        else if(items[position] == "김광석"){
            holder.image.setImageResource(R.drawable.kimstreet)
        }
        else if(items[position] == "매운탕"){
            holder.image.setImageResource(R.drawable.maeuntang)
        }
        else if(items[position] == "막창"){
            holder.image.setImageResource(R.drawable.makchang)
        }
        else if(items[position] == "무침회"){
            holder.image.setImageResource(R.drawable.muchimhoi)
        }
        else if(items[position] == "수성못"){
            holder.image.setImageResource(R.drawable.suseong)
        }
        else if(items[position] == "찜갈비"){
            holder.image.setImageResource(R.drawable.jjim)
        }
        else if(items[position] == "통닭"){
            holder.image.setImageResource(R.drawable.chicken)
        }
        else if(items[position] == "염소"){
            holder.image.setImageResource(R.drawable.goat)
        }
        else if(items[position] == "찐빵"){
            holder.image.setImageResource(R.drawable.jjinbbang)
        }
        else if(items[position] == "사과"){
            holder.image.setImageResource(R.drawable.apple)
        }
        else if(items[position] == "배"){
            holder.image.setImageResource(R.drawable.pear)
        }
        else if(items[position] == "쌀"){
            holder.image.setImageResource(R.drawable.rice)
        }
        else if(items[position] == "멸치"){
            holder.image.setImageResource(R.drawable.small_fish)
        }
        else if(items[position] == "고등어"){
            holder.image.setImageResource(R.drawable.fish)
        }
        else if(items[position] == "한우"){
            holder.image.setImageResource(R.drawable.cow)
        }
    }
}