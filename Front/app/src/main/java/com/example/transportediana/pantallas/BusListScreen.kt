package com.example.transportediana.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.transportediana.viewmodel.AutobusViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusListScreen(viewModel: AutobusViewModel) {
    // Llama a cargar los datos al iniciar la pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarAutobusesDeHoy()
    }

    val autobuses by viewModel.autobusesHoy.collectAsState()

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
        if (autobuses.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay autobuses hoy", fontSize = 18.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(autobuses) { item ->
                    ElevatedCard(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = Color(0xFFF5F5F5)
                        ),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "${item.ruta.origen} → ${item.ruta.destino}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("🚌 Tipo: ${item.autobus.tipo}")
                            Text("⏰ Salida: ${item.ruta.horarioSalida}")
                            Text("👥 Capacidad: ${item.autobus.capacidad}")
                        }
                    }
                }
            }
        }
    }
}
