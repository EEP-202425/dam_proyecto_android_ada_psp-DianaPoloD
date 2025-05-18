package com.example.transportediana.pantallas

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservaBilleteScreen(navController: NavHostController, autobusId: Long) {
    val pasajeroApi = PasajerosApi.retrofitService
    val billeteApi = BilletesApi.retrofitService
    val autobusApi = AutobusesApi.retrofitService

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var confirmacion by remember { mutableStateOf(false) }
    var modoEditar by remember { mutableStateOf(false) }

    var busReservado by remember { mutableStateOf<Autobus?>(null) }
    var pasajeroCreado by remember { mutableStateOf<Pasajero?>(null) }

    val scope = rememberCoroutineScope()

    // 🆕 Estado para mostrar mensajes flotantes (snackbar)
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        // 🆕 Añadimos snackbarHost al Scaffold para poder mostrar los mensajes
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            SmallTopAppBar(
                title = { Text("Reserva de Billete", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF1A237E))
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                OutlinedTextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

                Button(
                    onClick = {
                        scope.launch {
                            try {
                                val pasajero = Pasajero(0, nombre, apellido, email)
                                val pasajeroResp = pasajeroApi.createPasajero(pasajero)
                                pasajeroCreado = pasajeroResp.body() ?: throw Exception("Error creando pasajero")

                                val autobus = autobusApi.getById(autobusId).body()
                                    ?: throw Exception("No se encontró el autobús")

                                val billete = Billete(
                                    pasajero = pasajeroCreado!!,
                                    autobus = autobus,
                                    precio = 15.0,
                                    fechaCompra = LocalDate.now().toString()
                                )

                                val billeteResp = billeteApi.createBillete(billete)

                                if (billeteResp.isSuccessful) {
                                    busReservado = autobus
                                    confirmacion = true
                                    modoEditar = false
                                }
                            } catch (e: Exception) {
                                // 🆕 Mostrar mensaje de error si la reserva falla
                                snackbarHostState.showSnackbar("❌ Error al reservar: ${e.localizedMessage}")
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
            }

            if (confirmacion && busReservado != null) {
                AlertDialog(
                    onDismissRequest = { confirmacion = false },
                    title = { Text("🎟️ Resumen del billete") },
                    text = {
                        if (!modoEditar) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text("🚌 Tipo de autobús: ${busReservado!!.tipo}")
                                Text("📍 Ruta: ${busReservado!!.ruta?.origen} → ${busReservado!!.ruta?.destino}")
                                Text("⏰ Salida: ${busReservado!!.ruta?.horarioSalida?.take(5)}")
                                Divider()
                                Text("👤 Nombre: $nombre")
                                Text("👤 Apellido: $apellido")
                                Text("📧 Email: $email")
                                Spacer(modifier = Modifier.height(8.dp))

                                IconButton(onClick = { modoEditar = true }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar datos")
                                }
                            }
                        } else {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                                OutlinedTextField(value = apellido, onValueChange = { apellido = it }, label = { Text("Apellido") })
                                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                                Button(onClick = {
                                    scope.launch {
                                        try {
                                            pasajeroCreado?.let { pasajero ->
                                                val actualizado = pasajero.copy(nombre = nombre, apellido = apellido, email = email)
                                                val resp = pasajeroApi.updatePasajero(pasajero.id, actualizado)
                                                if (resp.isSuccessful) {
                                                    pasajeroCreado = actualizado
                                                    modoEditar = false
                                                } else {
                                                    // 🆕 Mostrar error si falla la actualización
                                                    snackbarHostState.showSnackbar("❌ Error al guardar los cambios")
                                                }
                                            }
                                        } catch (e: Exception) {
                                            snackbarHostState.showSnackbar("🔥 ${e.localizedMessage}")
                                        }
                                    }
                                }) {
                                    Text("Guardar cambios")
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        try {
                                            pasajeroCreado?.let { pasajero ->
                                                val deleteResp = pasajeroApi.deletePasajero(pasajero.id)
                                                if (deleteResp.isSuccessful) {
                                                    // 🆕 Mostrar mensaje de éxito al eliminar
                                                    snackbarHostState.showSnackbar("✅ Pasajero eliminado correctamente")

                                                    // Limpiar datos locales
                                                    confirmacion = false
                                                    busReservado = null
                                                    nombre = ""
                                                    apellido = ""
                                                    email = ""
                                                    pasajeroCreado = null
                                                    modoEditar = false
                                                } else {
                                                    // mostrar error si la API no responde bien
                                                    snackbarHostState.showSnackbar("❌ No se pudo eliminar el pasajero")
                                                }
                                            }
                                        } catch (e: Exception) {
                                            // mostrar excepción si hay un fallo grave
                                            snackbarHostState.showSnackbar("🔥 Error: ${e.localizedMessage}")
                                        }
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Eliminar")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    confirmacion = false
                                    busReservado = null
                                    nombre = ""
                                    apellido = ""
                                    email = ""
                                    modoEditar = false
                                    navController.navigate("confirmacion")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF1A237E),
                                    contentColor = Color.White
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Confirmar")
                            }
                        }
                    }
                )
            }
        }
    }
}
