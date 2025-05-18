import com.example.transportediana.clases.Autobus
import com.example.transportediana.clases.Pasajero


data class Billete(
    val id: Long? = null,
    val pasajero: Pasajero, // Usamos los IDs, no los objetos enteros
    val autobus: Autobus,
    val precio: Double,
    val fechaCompra: String
)


