package com.example.transportediana.clases

import kotlinx.serialization.Serializable

@Serializable
data class Pasajero(
    val id: Long? = null,
    val nombre: String,
    val apellido: String,
    val email: String,
    val contraseña: String? = null //  La contraseña normalmente no se recupera, solo se envía al crear/actualizar
)