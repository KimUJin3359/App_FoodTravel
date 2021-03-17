package com.example.myproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*

class MyAdapter(val items : ArrayList<MyData>, val region: String) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    lateinit var rdb: DatabaseReference

    interface OnItemClickListener{
        fun OnItemClick(holder: MyViewHolder, view: View, data: MyData, position: Int)
    }

    var itemClickListener: OnItemClickListener ?= null
    var itemClickListener2: OnItemClickListener ?= null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.name)
        var road: TextView = itemView.findViewById(R.id.road)
        var menu: TextView = itemView.findViewById(R.id.menu)
        var set: LinearLayout = itemView.findViewById(R.id.linear)
        var btn: ImageButton = itemView.findViewById(R.id.callbtn)
        var starbtn: ToggleButton = itemView.findViewById(R.id.starbutton)
        init{
            set.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }

            btn.setOnClickListener {
                itemClickListener2?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }

            starbtn.setOnClickListener {
                if(starbtn.isChecked){
                    var database = FirebaseDatabase.getInstance()
                    var table = database.getReference("Products/restaurants")
                    rdb.child(items[position].name)
                    var name = table.child(items[position].name)
                    name.child("name").setValue(items[position].name)
                    name.child("road").setValue(items[position].road)
                    name.child("menu").setValue(items[position].menu)
                    name.child("region").setValue(items[position].region)
                    name.child("tel").setValue(items[position].tel)
                }
                else{
                    rdb.child(items[position].name).removeValue()
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        rdb = FirebaseDatabase.getInstance().getReference("Products/restaurants")
        val query = FirebaseDatabase.getInstance().reference.child("Products/items").limitToLast(50)
        val option = FirebaseRecyclerOptions.Builder<Product>().setQuery(query, Product::class.java).build()

        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.road.text = items[position].road
        holder.menu.text = items[position].menu

        rdb.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.hasChild(items[position].name))
                    holder.starbtn.isChecked = true
                else
                    holder.starbtn.isChecked = false
            }
        })
    }

}