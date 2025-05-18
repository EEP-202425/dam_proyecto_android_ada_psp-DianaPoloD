import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.example.transportediana.viewmodel.UiState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.transportediana.clases.AutobusconRuta
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusListScreen(viewModel: AutobusViewModel,
    onReservarClick: (Long) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarAutobusesDeHoy()
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "Viajes de Hoy",
                        style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF1A237E))
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success -> {
                    val buses = (uiState as UiState.Success).buses
                    if (buses.isEmpty()) {
                        Text("No hay autobuses hoy")
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(buses) { item: AutobusconRuta ->
                                val ruta = item.ruta
                                val autobus = item.autobus

                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text("${ruta.origen} → ${ruta.destino}")
                                        Text("🚌 ${autobus.tipo}")
                                        Text("⏰ Llegada: ${ruta.horarioLlegada.substring(0,5)}")
                                        Text("⏰ Salida: ${ruta.horarioSalida.substring(0,5)}")
                                        Text("📅 Fecha: ${ruta.fechaViaje}")

                                        Button(
                                            onClick = {onReservarClick(autobus.id)},
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFF1A237E),
                                                contentColor = Color.White
                                            ),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text("Confirmar reserva")
                                        }

                                    }
                                    }
                                }
                            }
                        }
                    }  is UiState.Error -> {
                val mensaje = (uiState as UiState.Error).message
                Text("Error: $mensaje")
            }
                }


            }
        }
    }
