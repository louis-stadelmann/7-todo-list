package com.example.todolistcomplex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.todolistcomplex.model.ListItemModel

class AddPage : AppCompatActivity() {
    var items = arrayListOf<ListItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_page)

        manageIntentValue()
        manageCancel()
        manageAddTask()
    }

    fun manageIntentValue() {
        var returnedItem = intent.getSerializableExtra("ITEMS")

        if (returnedItem == null) {
            items = arrayListOf<ListItemModel>()
        } else {
            items = returnedItem as ArrayList<ListItemModel>
        }
    }

    fun manageAddTask() {
        val button = findViewById<Button>(R.id.btnAddPageAdd)
        val etTitle = findViewById<EditText>(R.id.etAddPageTitle)
        val etDescription = findViewById<EditText>(R.id.etAddPageDescription)

        button.setOnClickListener {
            if (TextUtils.isEmpty(etTitle.text)) {
                etTitle.error = "Enter a title"
            }
            if (TextUtils.isEmpty(etDescription.text)) {
                etDescription.error = "Enter a description"
            }

            if (!TextUtils.isEmpty(etTitle.text) && !TextUtils.isEmpty(etDescription.text)) {
                items += ListItemModel(
                    etTitle.text.toString(),
                    etDescription.text.toString()
                )

                gotoMainActivity()
            }
        }
    }

    fun manageCancel() {
        val button = findViewById<Button>(R.id.btnAddPageCancel)

        button.setOnClickListener {
            gotoMainActivity()
        }
    }

    fun gotoMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("ITEMS", items)
        startActivity(intent)
    }
}