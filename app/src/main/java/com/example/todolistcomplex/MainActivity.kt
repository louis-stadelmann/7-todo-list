package com.example.todolistcomplex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.todolistcomplex.adapter.ListItemAdapter
import com.example.todolistcomplex.databinding.ActivityMainBinding
import com.example.todolistcomplex.model.ListItemModel

class MainActivity : AppCompatActivity() {
    var items = arrayListOf<ListItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manageListView()
        manageAddTask()
        manageAddPageReturn()
        manageItemListClick()
    }

    fun manageListView() {
        val listView = findViewById<ListView>(R.id.lvMain)
        val adapter = ListItemAdapter(this, items)

        listView.adapter = adapter
    }

    fun manageItemListClick() {
        val listView = findViewById<ListView>(R.id.lvMain)

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, EditPage::class.java)
            intent.putExtra("ITEMS", items)
            intent.putExtra("position", position)
            startActivity(intent)
        }
    }

    fun manageAddTask() {
        val button = findViewById<Button>(R.id.btnMain)

        button.setOnClickListener {
            val intent = Intent(this, AddPage::class.java)
            intent.putExtra("ITEMS", items)
            startActivity(intent)
        }
    }

    fun manageAddPageReturn() {
        var returnedItem = intent.getSerializableExtra("ITEMS")

        if (returnedItem == null) {
            items = arrayListOf<ListItemModel>()
        } else {
            items = returnedItem as ArrayList<ListItemModel>
        }
        manageListView()
    }
}