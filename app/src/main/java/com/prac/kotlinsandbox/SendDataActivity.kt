package com.prac.kotlinsandbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_send_data.*

class SendDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_data)

        // acitivy_next로 넘어가는 Intent 구현
        main_next_Button.setOnClickListener {
            // var : 변수
            // val : 자바에서는 final 같이 변경되지 못하는 변수
            val intent = Intent(this, NextActivity::class.java)
            intent.putExtra("data", main_EditText.text.toString()) // getText() == text
            startActivity(intent)
        }
    }
}