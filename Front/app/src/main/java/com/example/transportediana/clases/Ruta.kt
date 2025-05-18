package com.example.transportediana.clases

data class Ruta(
    val id: Long,
    val destino: String,
    val origen: String,
    val horarioSalida: String,
    val horarioLlegada: String,
    val fechaViaje: String
)


