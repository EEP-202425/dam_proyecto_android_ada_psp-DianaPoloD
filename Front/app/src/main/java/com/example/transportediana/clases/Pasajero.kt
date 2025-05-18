package com.example.transportediana.clases


data class Pasajero(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val email: String,
    val contrasena: String? = null //  La contraseña normalmente no se recupera, solo se envía al crear/actualizar
)