package com.example.todolistcomplex.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.todolistcomplex.AddPage
import com.example.todolistcomplex.R
import com.example.todolistcomplex.model.ListItemModel

class ListItemAdapter(var context : Context, var items: ArrayList<ListItemModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): ListItemModel {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater
                = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.item_list, parent, false)

        val textView1 = rowView.findViewById<TextView>(R.id.tvTitle)
        val textView2 = rowView.findViewById<TextView>(R.id.tvDescription)

        val data = getItem(position)

        textView1.text = data.title
        textView2.text = data.description

        return rowView
    }
}