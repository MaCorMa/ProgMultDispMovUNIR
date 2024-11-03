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

    fun insertarAnimales(animal : Mascota){
        this.animales.add(animal)
        println("${animal.nombre} ha sido insertado en el inventario")
    }

    fun eliminarAnimales(nombre : String){
        val animal = animales.find { it.nombre==nombre }
        if (animal != null){
            this.animales.remove(animal)
            println("${animal.nombre} ha sido eliminado del inventario")
        }  else {
                println("No se encuentra al animal")
        }
    }

    fun vaciarInventario(){

        this.animales.clear()
        println("Se han borrado todos los animales del inventario")
    }
}