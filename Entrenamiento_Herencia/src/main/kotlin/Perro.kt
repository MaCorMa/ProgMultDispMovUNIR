import java.util.*

class Perro (nombre:String, edad:Int, estado: String, fechaNacimiento: Date,
             val raza:String, var pulgas:Boolean) : Mascota(nombre, edad, estado, fechaNacimiento){

                 
    override fun muestra() {
        println("Esta mascota es un perro - Nombre $nombre - Edad $edad - Fecha $fechaNacimiento - Raza: $raza")
    }

    override fun cumpleanos() {
        println("El cumple del perrete es ${fechaNacimiento.toString()}")
    }

    override fun morir() {
        println("DEP $nombre el perrete")
    }

    override fun habla() {
        println("Los perretes decimos guau")
    }
}