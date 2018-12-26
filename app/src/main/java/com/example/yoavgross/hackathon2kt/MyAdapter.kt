package com.example.yoavgross.hackathon2kt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell.view.*

class MyAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    var list = emptyList<DbItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflater.inflate(R.layout.cell, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setAist(list : List<DbItem>){
        this.list =  list
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.text_item.text = list[position].name
    }

}