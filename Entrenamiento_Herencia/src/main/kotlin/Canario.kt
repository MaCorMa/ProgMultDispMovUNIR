import java.util.*

class Canario (nombre:String, edad:Int, estado: String, fechaNacimiento: Date, pico:String, vuela :Boolean,
               val color :String, val canta:Boolean) :Aves(nombre, edad, estado, fechaNacimiento, pico, vuela) {

    override fun volar() {
        if (this.vuela){
            println("El canario ${this.nombre} nombre vuela")
        }else{
            println("El canario ${this.nombre} nombre no vuela")
        }
    }

    override fun muestra() {
        println("Esta mascota es un canario - Nombre $nombre - Edad $edad - Fecha $fechaNacimiento - color: $color")
    }

    override fun cumpleanos() {
        println("El cumple del canario es ${fechaNacimiento.toString()}")
    }

    override fun morir() {
        println("DEP $nombre el canario")
    }

    override fun habla() {
        println("Los canarios cantamos")
    }
}
