package com.example.exercise_call_api_retrofit

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.exercise_call_api_retrofit.R.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiInterface {
    @GET("posts")
    fun getData(): Call<List<MyDataItem>>
}

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        //GUARDARE IL LOGCAT O IL RUN DOPO AVER RUNNATO
        getMyData()

    }

    private fun getMyData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofit.getData()
        retrofitData.enqueue(object :Callback<List<MyDataItem>?>{
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                Log.d("MainActivity","The repo work? ${response.isSuccessful} ")
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity","ERROR! ${t.message}")
            }

        })
    }
}
