open class Persona (val nombre:String) {

    open fun registrarCenso(localidad:String){
        println("Te has censado en {$localidad}")
    }
}