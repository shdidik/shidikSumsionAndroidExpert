package com.dicoding.tugas.core.data.detailData.remote.network

import com.dicoding.tugas.core.data.detailData.remote.response.ListTourismResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListTourismResponse
}
