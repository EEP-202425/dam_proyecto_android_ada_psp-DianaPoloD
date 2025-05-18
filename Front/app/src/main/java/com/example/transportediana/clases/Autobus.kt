package com.example.transportediana.clases

data class Autobus(
    val id: Long ,
    val tipo: String,
    val capacidad: Int,
    val ruta: Ruta? //  Puede ser nulo al crear, y no es necesario enviarlo en la request, se gestiona en el backend

)