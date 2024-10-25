abstract class Animal {

    abstract val name:String

    abstract fun asignarDueno()

    fun comer (comida:String){
        println("Hoy he comido $comida")
    }
}