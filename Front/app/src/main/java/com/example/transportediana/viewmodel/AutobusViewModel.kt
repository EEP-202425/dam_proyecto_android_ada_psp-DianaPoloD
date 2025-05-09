package com.example.transportediana.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transportediana.api.AutobusesApi
import com.example.transportediana.api.RutasApi
import com.example.transportediana.clases.AutobusconRuta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class AutobusViewModel : ViewModel() {

    private val _autobusesHoy = MutableStateFlow<List<AutobusconRuta>>(emptyList())
    val autobusesHoy: StateFlow<List<AutobusconRuta>> get() = _autobusesHoy

    fun cargarAutobusesDeHoy() {
        viewModelScope.launch {
            try {
                val autobusesResp = AutobusesApi.retrofitService.getAll()
                val rutasResp = RutasApi.retrofitService.getAll()

                if (autobusesResp.isSuccessful && rutasResp.isSuccessful) {
                    val fechaHoy = LocalDate.now()
                    val rutas = rutasResp.body() ?: emptyList()
                    val autobuses = autobusesResp.body() ?: emptyList()

                    val filtrado = autobuses.mapNotNull { autobus ->
                        val ruta = rutas.find { it.id == autobus.rutaId && it.fechaViaje == fechaHoy }
                        ruta?.let {
                            AutobusconRuta(autobus, it)
                        }
                    }

                    _autobusesHoy.value = filtrado
                }

            } catch (e: Exception) {
                // Puedes manejar errores aquí (mostrar en UI, logs, etc.)
            }
        }
    }
}
