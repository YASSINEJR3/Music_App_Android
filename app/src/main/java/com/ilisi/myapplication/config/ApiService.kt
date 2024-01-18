package com.ilisi.myapplication.config

import com.ilisi.myapplication.models.MusicList
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("/api/v2/us/music/most-played/10/albums.json")
    fun getMusics(): Call<MusicList>
}