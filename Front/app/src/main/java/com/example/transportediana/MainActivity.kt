// MainActivity.kt
package com.example.transportediana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.transportediana.ui.screen.BusListScreen
import com.example.transportediana.viewmodel.AutobusViewModel
import com.example.transportediana.ui.theme.TransporteDianaTheme

class MainActivity : ComponentActivity() {
    private val viewModel: AutobusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TransporteDianaTheme {
                BusListScreen(viewModel = viewModel)
            }
        }
    }
}
