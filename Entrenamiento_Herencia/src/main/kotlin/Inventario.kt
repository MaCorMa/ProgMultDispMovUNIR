class Inventario {


     private val animales: MutableList<Mascota> = mutableListOf()

    fun mostrarAnimales(){
        if (animales.isEmpty()){
            println("No hay animales en nuestro inventario")
        }else{
            animales.forEach{ animales ->
                println("${animales::class.simpleName} - Nombre: ${animales.nombre}")
            }
        }
    }

    fun msotrarDatosAnimal(nombre:String){
        val animal = animales.find { it.nombre == nombre }

        if(animal!=null){
            animal.muestra()
        }else{
          println("No hay animales en el inventario")
        }
    }

    fun mostrarAnimalesCompleto(){
        if (animales.isEmpty()){
            println("No hay animales en nuestro inventario")
        }else{
            animales.forEach{ animales ->
                animales.muestra()
            }
        }
    }
}