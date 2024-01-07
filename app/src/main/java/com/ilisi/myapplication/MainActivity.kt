package com.ilisi.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ilisi.myapplication.models.MusicList
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val musicJsonString = readJsonFile(R.raw.music)
        val gson = Gson()
        val musicList = gson.fromJson(musicJsonString, MusicList::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = TrackAdapter(musicList.entry)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun readJsonFile(resourceId: Int): String {
        val inputStream: InputStream = resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }

}