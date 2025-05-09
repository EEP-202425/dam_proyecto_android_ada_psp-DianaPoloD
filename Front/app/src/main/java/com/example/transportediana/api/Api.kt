package com.example.transportediana.api

import com.example.transportediana.clases.Pasajero
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetroFitClient {
    private const val BASE_URL ="http://10.0.2.2:3000/"

     val retrofit = Retrofit.Builder()// para que lo llamen de otra fun
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}


