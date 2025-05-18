import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transportediana.api.AutobusesApi
import com.example.transportediana.api.RutasApi
import com.example.transportediana.clases.AutobusconRuta
import com.example.transportediana.viewmodel.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class AutobusViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> get() = _uiState

    fun cargarAutobusesDeHoy() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val autobusesResp = AutobusesApi.retrofitService.getAll()

                if (autobusesResp.isSuccessful) {
                    val autobuses = autobusesResp.body() ?: emptyList()
                    val fechaHoy = LocalDate.now()

                    val filtrado = autobuses.filter { autobus ->
                        autobus.ruta?.fechaViaje?.let { fechaString ->
                            LocalDate.parse(fechaString) == fechaHoy
                        } ?: false
                    }
                        .take(3) // 👈 solo los primeros 3
                        .map { autobus ->
                            AutobusconRuta(autobus, autobus.ruta!!)
                        }



                    _uiState.value = UiState.Success(filtrado)
                } else {
                    _uiState.value = UiState.Error("Error en la respuesta del servidor")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Excepción: ${e.message}")
            }
        }
    }
}
