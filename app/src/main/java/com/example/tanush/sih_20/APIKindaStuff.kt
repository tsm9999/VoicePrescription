package com.example.tanush.sih_20

import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

class APIKindaStuff {

    interface APIService {


        @Headers("Content-type: application/json")
        @POST("/api/post_some_data/{user1}")
        fun greetUser1(@Path("user1") user1: String): Call<ResponseBody>
    }

    companion object {
        private val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.43.25:5005/")
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()

        var service = retrofit.create(APIService::class.java)
    }
}