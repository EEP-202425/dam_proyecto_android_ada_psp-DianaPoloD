package com.example.transportediana.api

import com.example.transportediana.clases.Ruta
import retrofit2.Response
import retrofit2.http.*

interface RutasApiService {
    @GET("/rutas")
    suspend fun getAll(): Response<List<Ruta>>

    @GET("/rutas/{id}")
    suspend fun getById(@Path("id") id: Long): Response<Ruta>

    @POST("/rutas")
    suspend fun create(@Body ruta: Ruta): Response<Ruta>

    @PUT("/rutas/{id}")
    suspend fun update(@Path("id") id: Long, @Body ruta: Ruta): Response<Ruta>

    @DELETE("/rutas/{id}")
    suspend fun delete(@Path("id") id: Long): Response<Void>
}

object RutasApi {
    val retrofitService: RutasApiService by lazy {
        RetroFitClient.retrofit.create(RutasApiService::class.java)
    }
}
