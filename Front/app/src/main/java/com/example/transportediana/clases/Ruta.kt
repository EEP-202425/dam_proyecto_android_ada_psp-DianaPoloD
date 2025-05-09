package com.example.transportediana.clases
import java.time.LocalDate

data class Ruta(
    val id: Long? = null,
    val destino: String,
    val origen: String,
    val horarioSalida: LocalDate,
    val horarioLlegada: LocalDate,
    val fechaViaje: LocalDate
    //val autobuses: List<Autobus>? = null  No es necesario para crear/actualizar, se maneja en el backend
)