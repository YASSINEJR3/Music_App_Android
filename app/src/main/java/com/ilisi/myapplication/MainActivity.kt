package com.ilisi.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilisi.myapplication.models.MusicList
import com.ilisi.myimagesapplication.networking.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var musicList: MusicList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMusics()

    }

    private fun showMusics() {

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
                musicList = responseBody
                handleMusicList()
            }

            override fun onFailure(call: Call<MusicList>, t: Throwable) {
                print("Error: ${t.message}")
                t.printStackTrace()
            }

        })
    }

    private fun handleMusicList() {
        print("MusicList: ${musicList.feed.entry[0].title.label}")
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = TrackAdapter(musicList.feed.entry)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}