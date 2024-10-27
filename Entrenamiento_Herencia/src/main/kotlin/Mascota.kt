import java.util.*

abstract class Mascota (val nombre:String, var edad:Int, var estado:String,
                        val fechaNacimiento: Date) {
    abstract fun muestra()
    abstract fun cumpleanos()
    abstract fun morir()
    abstract fun habla()
}