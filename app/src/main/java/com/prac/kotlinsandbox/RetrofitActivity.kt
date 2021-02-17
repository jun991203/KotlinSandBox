package com.prac.kotlinsandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        val BASE_URL_NAVER_PAPAGO = "https://openapi.naver.com/"
        val CLIENT_ID = "---"
        val CLIENT_SECRET = "---"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NAVER_PAPAGO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(NaverAPI::class.java)

        retrofit_btn.setOnClickListener {
            var english_text = retrofit_english!!.text
            val callPostTransferPapago = api.transferPapago(CLIENT_ID, CLIENT_SECRET, "en", "ko",
                english_text.toString()
            )

            callPostTransferPapago.enqueue(object: Callback<ResultTransferPapago>{
                override fun onResponse(
                    call: Call<ResultTransferPapago>,
                    response: Response<ResultTransferPapago>
                ) {
                    Log.e("Retrofit", "성공 : ${response.body().toString()}")
                    val body = response.body()
                    retrofit_korean.text = body!!.message.result.translatedText
                }

                override fun onFailure(call: Call<ResultTransferPapago>, t: Throwable) {
                    Log.e("Retrofit", "실패 : $t")
                }
            })
        }




    }
}