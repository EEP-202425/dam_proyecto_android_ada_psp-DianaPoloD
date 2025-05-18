package com.example.transportediana.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.transportediana.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmacionScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Billete Confirmado", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF1A237E))
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
               painter = painterResource(id = R.drawable.ic_launcher),
                contentDescription = "Logo Tolemadriz",
            modifier = Modifier.height(100.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "🎟️ Billete confirmado",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF1A237E)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Gracias por elegirnos. El resumen ha sido enviado a su correo. ✉️",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}
