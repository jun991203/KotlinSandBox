package com.prac.kotlinsandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        if(intent.hasExtra("data")) {
            // setText() == text
            next_answer_TextView.text = intent.getStringExtra("data")
        }
    }
}