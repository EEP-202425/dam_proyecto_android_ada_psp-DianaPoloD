package com.example.transportediana.api

import com.example.transportediana.clases.Pasajero
import retrofit2.Response
import retrofit2.http.*

interface PasajerosApiService {
    @GET("/pasajeros")
    suspend fun getAllPasajeros(): Response<List<Pasajero>>

    @GET("/pasajeros/{id}")
    suspend fun getPasajeroById(@Path("id") id: Long): Response<Pasajero>

    @POST("/pasajeros")
    suspend fun createPasajero(@Body pasajero: Pasajero): Response<Pasajero>

    @PUT("/pasajeros/{id}")
    suspend fun updatePasajero(@Path("id") id: Long, @Body pasajero: Pasajero): Response<Pasajero>

    @DELETE("/pasajeros/{id}")
    suspend fun deletePasajero(@Path("id") id: Long): Response<Void>
}

// OJO: esto es una clase singleton, y se usa bien el lazy
object PasajerosApi {
    val retrofitService: PasajerosApiService by lazy {
        RetroFitClient.retrofit.create(PasajerosApiService::class.java)
    }
}
