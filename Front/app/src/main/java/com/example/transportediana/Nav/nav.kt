package com.example.transportediana.nav

import AutobusViewModel
import BusListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.transportediana.pantallas.ConfirmacionScreen
import com.example.transportediana.pantallas.ReservaBilleteScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "listado") {

        composable("listado") {
            val viewModel: AutobusViewModel = viewModel()
            BusListScreen(
                viewModel = viewModel,
                onReservarClick = { autobusId ->
                    navController.navigate("reserva/$autobusId")
                }
            )
        }

        composable("reserva/{autobusId}") { backStackEntry ->
            val autobusId = backStackEntry.arguments?.getString("autobusId")?.toLongOrNull() ?: 0L
            ReservaBilleteScreen(navController = navController, autobusId = autobusId)
        }

        composable("confirmacion") {
            ConfirmacionScreen()
        }
    }
}

