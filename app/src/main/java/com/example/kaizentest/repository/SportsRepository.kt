package com.example.kaizentest.repository

import com.example.kaizentest.api.KaizenApi
import com.example.kaizentest.api.RetrofitClient
import com.example.kaizentest.model.Sports
import io.reactivex.Observable

class SportsRepository {

    private val client: KaizenApi = RetrofitClient.retrofit

    fun getAll(): Observable<ArrayList<Sports>> = client.getAllSports()
}