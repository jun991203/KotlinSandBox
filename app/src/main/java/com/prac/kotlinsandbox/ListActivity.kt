package com.prac.kotlinsandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var items = arrayListOf<ListData> (
            ListData("Row 1"),
            ListData("Row 2"),
            ListData("Row 3")
        )

        list_RecyclerView.adapter = ListAdapter(this, items)
        list_RecyclerView.layoutManager = LinearLayoutManager(this)
    }
}