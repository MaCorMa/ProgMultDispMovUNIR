import java.util.*

class Loro (nombre:String, edad:Int, estado: String, fechaNacimiento: Date, pico:String, vuela :Boolean,
            val origen :String, val habla:Boolean) : Aves(nombre, edad, estado, fechaNacimiento, pico, vuela) {


    override fun volar() {
        if (this.vuela){
            println("El loro ${this.nombre} nombre vuela")
        }else{
            println("El loro ${this.nombre} nombre no vuela")
        }
    }

    override fun muestra() {
        println("Esta mascota es un loro - Nombre $nombre - Edad $edad - Fecha $fechaNacimiento - origen: $origen")
    }

    override fun cumpleanos() {
        println("El cumple del lorete es ${fechaNacimiento.toString()}")
    }

    override fun morir() {
        println("DEP $nombre el lorete")
    }

    override fun habla() {
        println("Los loretes decimos de todo")
    }
}