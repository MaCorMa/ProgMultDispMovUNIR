import java.text.SimpleDateFormat
import java.util.*

class Movimiento (val id: Int, val tipo:String, val cantidad:Double){ // se definen propiedades

    val fecha:String = this.obtenerFechaActual()


    //para la propiedad fecha
    private fun obtenerFechaActual():String{
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
    }

    override fun toString(): String{
        return "ID: $id, tipo: $tipo, cantidad: $cantidad, fecha: $fecha"
    }

}