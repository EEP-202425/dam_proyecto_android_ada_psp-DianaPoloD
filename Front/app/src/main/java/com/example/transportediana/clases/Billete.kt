
import java.time.LocalDate



data class Billete(
    val id: Long? = null,
    val pasajeroId: Long, // Usamos los IDs, no los objetos enteros
    val autobusId: Long,
    val precio: Double,
    val fechaCompra: LocalDate
)


