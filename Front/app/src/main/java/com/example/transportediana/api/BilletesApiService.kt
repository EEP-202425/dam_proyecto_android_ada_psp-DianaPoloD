package com.example.transportediana.api

import Billete
import retrofit2.Response
import retrofit2.http.*

interface BilletesApiService {
    @GET("/billetes")
    suspend fun getAllBilletes(): Response<List<Billete>>

    @GET("/billetes/{id}")
    suspend fun getBilleteById(@Path("id") id: Long): Response<Billete>

    @POST("/billetes")
    suspend fun createBillete(@Body billete: Billete): Response<Billete>

    @PUT("/billetes/{id}")
    suspend fun updateBillete(@Path("id") id: Long, @Body billete: Billete): Response<Billete>

    @DELETE("/billetes/{id}")
    suspend fun deleteBillete(@Path("id") id: Long): Response<Void>

    // Endpoint adicional que podría ser útil
    @GET("/billetes/pasajero/{pasajeroId}")
    suspend fun getBilletesByPasajeroId(@Path("pasajeroId") pasajeroId: Long): Response<List<Billete>>

    // Endpoint adicional para buscar billetes por autobus
    @GET("/billetes/autobus/{autobusId}")
    suspend fun getBilletesByAutobusId(@Path("autobusId") autobusId: Long): Response<List<Billete>>
}

object BilletesApi {
    val retrofitService: PasajerosApiService by lazy {
        RetroFitClient.retrofit.create(PasajerosApiService::class.java)
    }
}
