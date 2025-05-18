package com.example.transportediana.clases

data class Autobus(
    val id: Long ,
    val tipo: String,
    val capacidad: Int,
    val ruta: Ruta

)