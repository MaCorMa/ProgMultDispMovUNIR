import java.util.*

class Gato (nombre:String, edad:Int, estado: String, fechaNacimiento: Date,
            val color:String, val peloLargo :Boolean) : Mascota(nombre, edad, estado, fechaNacimiento) {

    override fun muestra() {
        println("Esta mascota es un gato - Nombre $nombre - Edad $edad - Fecha $fechaNacimiento - color: $color")
    }

    override fun cumpleanos() {
        println("El cumple del gatete es ${fechaNacimiento.toString()}")
    }

    override fun morir() {
        println("DEP $nombre el gatete")
    }

    override fun habla() {
        println("Los gatetes decimos miau")
    }
}