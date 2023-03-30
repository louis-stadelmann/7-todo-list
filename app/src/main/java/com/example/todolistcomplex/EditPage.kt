package com.example.todolistcomplex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.todolistcomplex.model.ListItemModel

class EditPage : AppCompatActivity() {
    var items = arrayListOf<ListItemModel>()
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_page)

        manageIntentValue()
        putValueInEditText()
        manageEditButton()
        manageCancelButton()
        manageDeleteButton()
    }

    fun manageIntentValue() {
        var returnedItem = intent.getSerializableExtra("ITEMS")
        var returnedPosition = intent.getIntExtra("position", 0)

        if (returnedItem == null) {
            items = arrayListOf<ListItemModel>()
        } else {
            items = returnedItem as ArrayList<ListItemModel>
        }

        position = returnedPosition
    }

    fun putValueInEditText() {
        val etTitle = findViewById<EditText>(R.id.etEditPageTitle)
        val etDescription = findViewById<EditText>(R.id.etEditPageDescription)

        if (items.size > position) {
            etTitle.setText(items[position].title)
            etDescription.setText(items[position].description)
        }

    }

    fun manageEditButton() {
        val button = findViewById<Button>(R.id.btnEditPageEdit)
        val etTitle = findViewById<EditText>(R.id.etEditPageTitle)
        val etDescription = findViewById<EditText>(R.id.etEditPageDescription)

        button.setOnClickListener {
            if (TextUtils.isEmpty(etTitle.text)) {
                etTitle.error = "Enter a title"
            }
            if (TextUtils.isEmpty(etDescription.text)) {
                etDescription.error = "Enter a description"
            }
            if (!TextUtils.isEmpty(etTitle.text) && !TextUtils.isEmpty(etDescription.text)) {
                items[position].title = etTitle.text.toString()
                items[position].description = etDescription.text.toString()

                gotoMainActivity()
            }
        }
    }

    fun manageCancelButton() {
        val button = findViewById<Button>(R.id.btnEditPageCancel)

        button.setOnClickListener {
            gotoMainActivity()
        }
    }

    fun manageDeleteButton() {
        val button = findViewById<Button>(R.id.btnEditPageDelete)

        button.setOnClickListener {
            items = ArrayList(items).apply { removeAt(position) }

            gotoMainActivity()
        }
    }

    fun gotoMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("ITEMS", items)
        startActivity(intent)
    }
}