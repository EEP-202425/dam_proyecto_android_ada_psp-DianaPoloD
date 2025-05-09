package com.example.transportediana.clases
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class Ruta(
    val id: Long? = null,
    val destino: String,
    val origen: String,
    val horarioSalida: String,
    val horarioLlegada: String,
    val fechaViaje: String
    //val autobuses: List<Autobus>? = null  No es necesario para crear/actualizar, se maneja en el backend
)