package com.example.kaizentest.api

import com.example.kaizentest.model.Sports
import io.reactivex.Observable
import retrofit2.http.GET

interface KaizenApi {
    @GET("api/sports")
    fun getAllSports(): Observable<ArrayList<Sports>>
}