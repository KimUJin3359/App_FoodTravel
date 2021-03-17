package com.example.myproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class MyProductAdapter(options: FirebaseRecyclerOptions<Product>) :
    FirebaseRecyclerAdapter<Product, MyProductAdapter.ViewHolder>(options){

    interface OnItemClickListener{
        fun OnItemClick(view:View, position:Int)
    }

    var itemClickListener: OnItemClickListener ?= null
    var itemClickListener2 : OnItemClickListener ?= null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.name)
        var road: TextView = itemView.findViewById(R.id.road)
        var menu: TextView = itemView.findViewById(R.id.menu)
        var set: LinearLayout = itemView.findViewById(R.id.linear)
        var btn: ImageButton = itemView.findViewById(R.id.callbtn)
        var starbtn: ToggleButton = itemView.findViewById(R.id.starbutton)
        init{
            set.setOnClickListener {
                itemClickListener?.OnItemClick(it, adapterPosition)
            }

            btn.setOnClickListener {
                itemClickListener2?.OnItemClick(it, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProductAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyProductAdapter.ViewHolder,
        position: Int,
        model: Product
    ) {
        holder.name.text = model.name
        holder.road.text = model.road
        holder.menu.text = model.menu
        holder.starbtn.visibility = View.GONE
    }


}