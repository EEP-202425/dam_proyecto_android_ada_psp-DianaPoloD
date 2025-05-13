package com.example.transportediana.screens

import Billete
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.transportediana.api.*
import com.example.transportediana.clases.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import androidx.compose.ui.text.input.PasswordVisualTransformation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservaBilleteScreen(autobusId: Long) {
    val pasajeroApi = PasajerosApi.retrofitService
    val billeteApi = BilletesApi.retrofitService
    val autobusApi = AutobusesApi.retrofitService

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf<String?>(null) }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Reserva de Billete", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF1A237E))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            OutlinedTextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") })
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

            // Contraseña oculta
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()//para que no se vea la contraseña
            )

            Button(
                onClick = {
                    scope.launch {
                        try {
                            val pasajero = Pasajero(
                                id = 0,
                                nombre = nombre,
                                apellido = apellido,
                                email = email,
                                contrasena = contrasena
                            )
                            val pasajeroResp = pasajeroApi.createPasajero(pasajero)
                            val pasajeroCreado = pasajeroResp.body() ?: throw Exception("Error creando pasajero")

                            val autobusResp = autobusApi.getById(autobusId)
                            val autobus = autobusResp.body() ?: throw Exception("No se encontró el autobús")

                            val billete = Billete(
                                pasajero = pasajeroCreado,
                                autobus = autobus,
                                precio = 15.0,
                                fechaCompra = LocalDate.now().toString()
                            )

                            val billeteResp = billeteApi.createBillete(billete)

                            if (billeteResp.isSuccessful) {
                                mensaje = "🎉 ¡Billete reservado correctamente!"
                                // Limpiar campos después de éxito
                                nombre = ""
                                apellido = ""
                                email = ""
                                contrasena = ""
                            } else {
                                mensaje = "Error al crear billete: código ${billeteResp.code()}"
                            }
                        } catch (e: Exception) {
                            mensaje = "Error: ${e.message}"
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A237E),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar reserva")
            }

            mensaje?.let {
                Text(it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
