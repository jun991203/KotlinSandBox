package com.prac.kotlinsandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        var check = 0
        image_change_Button.setOnClickListener {
            if(check == 0){
                image_ImageView.setImageResource(R.drawable.ic_baseline_adb_24)
                check = 1
            }else{
                image_ImageView.setImageResource(R.drawable.ic_android_black_24dp)
                check = 0
            }
            Toast.makeText(this@ImageActivity, "이미지를 변경합니다.", Toast.LENGTH_SHORT).show()
            /*
                [@; Symbol 'at']
                > introduces an annotation
                > introduces or references a loop label
                > introduces or references a lambda label
                > references a 'this' expression from an outer scope
                > references an outer superclass
             */
        }
    }
}