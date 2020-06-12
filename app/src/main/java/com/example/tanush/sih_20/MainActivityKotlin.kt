package com.example.tanush.sih_20

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        stt.setText(str)
        val s = "Patient name is kavita sultanpure . Patient id is 1405. Take CEFIZOX 250 mg Injection before lunch after dinner for 2 days. Take Bronkotab EX Tablet after breakfast for 17 days. Take Cefizox 1 mg Injection before dinner for 5 days. Diagnosis 1 viral fever. Symptom 1 Weakness. Symptom 2 Cough. Advice 1 take steam. Advice 2 use warm cloths. stop";


        btnPOST.setOnClickListener {
//            val jsonObj = JsonObject()
//            jsonObj.addProperty("transcript", str)
//                Log.e("error",str.toString())
            //  POST demo
            APIKindaStuff
                    .service
                    .greetUser1("Patient name is kavita sultanpure . Patient id is 1405. Take CEFIZOX 250 mg Injection before lunch after dinner for 2 days. Take Bronkotab EX Tablet after breakfast for 17 days. Take Cefizox 1 mg Injection before dinner for 5 days. Diagnosis 1 viral fever. Symptom 1 Weakness. Symptom 2 Cough. Advice 1 take steam. Advice 2 use warm cloths. stop")
//
//
//                    .greetUser1("patient name is Pratik Patil. patient ID is ABC. take Alpha tablet after lunch for 5 days. diagnosis viral fever. symptom weakness . advice take steam.")
                    //       .greetUser1(str.toString())
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("---TTTT :: POST Throwable EXCEPTION:: " + t.message)
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                val msg = response.body()?.string()
                                println("---TTTT :: POST msg from server :: $msg")
//                                val intent = Intent(baseContext, thisMainActivityKotlin)
                                if (msg is String) {
                                    Log.e("he", "no. is string")
                                }
                                msg.toString();
                                //intent.putExtra("mess",msg.toString())
                                //startActivity(intent)
                                jsonPrescription.text = msg
                            }
                        }
                    })
        }
        btnPres.setOnClickListener {
            val intent1 = Intent(applicationContext, PrescriptionActivity::class.java)
            intent.putExtra("message", s)
            startActivity(intent1)


        }





    }
}