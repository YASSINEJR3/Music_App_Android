package com.ilisi.myapplication.services

import com.ilisi.myapplication.models.MusicList
import retrofit2.http.GET

interface MusicApiService {
    @GET("/topsongs/limit=100/json")
    suspend fun getMusic(): MusicList
}