package com.prac.kotlinsandbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_senddata_Button.setOnClickListener {
            val dataIntent = Intent(this, SendDataActivity::class.java)
            startActivity(dataIntent)
        }

        main_image_Button.setOnClickListener {
            val imageIntent = Intent(this, ImageActivity::class.java)
            startActivity(imageIntent)
        }

        main_recycler_Button.setOnClickListener {
            val reIntent = Intent(this, ListActivity::class.java)
            startActivity(reIntent)
        }

        main_location_Button.setOnClickListener {
            val locIntent = Intent(this, LocationActivity::class.java)
            startActivity(locIntent)
        }


    }
}