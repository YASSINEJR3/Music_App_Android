package com.ilisi.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ilisi.myapplication.models.MusicList
import com.ilisi.myimagesapplication.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMusics()
        /*val musicJsonString = readJsonFile(R.raw.music)
        val gson = Gson()
        val musicList = gson.fromJson(musicJsonString, MusicList::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = TrackAdapter(musicList.entry)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter*/
    }

    private fun readJsonFile(resourceId: Int): String {
        val inputStream: InputStream = resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }


    fun getMusics() {

        val client = ApiConfig.getApiService().getMusics()

        client.enqueue(object : Callback<MusicList> {

            override fun onResponse(
                call: Call<MusicList>,
                response: Response<MusicList>
            ) {
                val responseBody = response.body()
                print("Response: ${responseBody.toString()}") // "Response: OK
                if (!response.isSuccessful || responseBody == null) {
                    print("Error: ${response.code()}");
                    return
                }

            }

            override fun onFailure(call: Call<MusicList>, t: Throwable) {
                print("Error: ${t.message}")
                t.printStackTrace()
            }

        })
    }

}