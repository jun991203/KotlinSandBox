package com.prac.kotlinsandbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val context: Context, private val data: ArrayList<ListData>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(v: View): RecyclerView.ViewHolder(v) {
        private val rowName = itemView.findViewById<TextView>(R.id.row_TextView)

        fun bind(data: ListData, context: Context){
            rowName.text = data.rowName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.row_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position], context)
    }

    override fun getItemCount(): Int  = data.size

}
