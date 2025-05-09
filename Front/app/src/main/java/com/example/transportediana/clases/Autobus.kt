package com.example.transportediana.clases

import kotlinx.serialization.Serializable

@Serializable
data class Autobus(
    val id: Long? = null,
    val tipo: String,
    val capacidad: Int,
    val rutaId: Long? = null //  Puede ser nulo al crear, y no es necesario enviarlo en la request, se gestiona en el backend

)