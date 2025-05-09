package com.example.transportediana.api

import com.example.transportediana.clases.Autobus
import retrofit2.Response
import retrofit2.http.*

interface AutobusesApiService {
    @GET("/autobuses")
    suspend fun getAll(): Response<List<Autobus>>

    @GET("/autobuses/{id}")
    suspend fun getById(@Path("id") id: Long): Response<Autobus>

    @POST("/autobuses")
    suspend fun create(@Body autobus: Autobus): Response<Autobus>

    @PUT("/autobuses/{id}")
    suspend fun update(@Path("id") id: Long, @Body autobus: Autobus): Response<Autobus>

    @DELETE("/autobuses/{id}")
    suspend fun delete(@Path("id") id: Long): Response<Void>
}

object AutobusesApi {
    val retrofitService: AutobusesApiService by lazy {
        RetroFitClient.retrofit.create(AutobusesApiService::class.java)
    }
}
