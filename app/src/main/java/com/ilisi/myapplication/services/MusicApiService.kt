package com.ilisi.myapplication.services

import com.ilisi.myapplication.models.MusicList
import retrofit2.Call
import retrofit2.http.*


interface MusicApiService {

    @GET("us/rss/topalbums/limit=100/json")
    fun getMusics(): Call<MusicList>
}