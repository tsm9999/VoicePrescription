package com.example.tanush.sih_20

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.api_test.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.api_test)
        val intent = intent
        val str = intent.getStringExtra("message")
        stt.text = str
        btnPOST.setOnClickListener {
//            val jsonObj = JsonObject()
//            jsonObj.addProperty("transcript", str)
                Log.e("error",str.toString())
            //  POST demo
            APIKindaStuff
                    .service
//                    .greetUser1("Patient name is kavita sultanpure . Patient id is 1405. Take CEFIZOX 250 mg Injection before lunch after dinner for 2 days. Take Bronkotab EX Tablet after breakfast for 17 days. Take Cefizox 1 mg Injection before dinner for 5 days. Diagnosis 1 viral fever. Symptom 1 Weakness. Symptom 2 Cough. Advice 1 take steam. Advice 2 use warm cloths. stop"
                    .greetUser1(str.toString())
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("---TTTT :: POST msg from server :: " + msg)
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }

        btnGET.setOnClickListener {
            APIKindaStuff
                    .service
                    .greetUser("MADDI")
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("---TTTT :: GET Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("---TTTT :: GET msg from server :: " + msg)
                                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
        }



    }
}