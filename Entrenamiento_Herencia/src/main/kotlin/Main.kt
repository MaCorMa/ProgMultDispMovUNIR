import java.util.*

fun main(){

    val inventario = Inventario()

    inventario.insertarAnimales(Perro("Tirant", 5, "Pesat", Date(), "Border Collie", false))
    inventario.insertarAnimales(Gato("Gatorro", 7, "Perillós", Date(), "Blanc i negre", false))
    inventario.insertarAnimales(Loro("Periquita", 20, "Parlador", Date(), "Gran", true,"Colombia", true))
    inventario.insertarAnimales(Canario("Piolin", 2, "Saludable", Date(), "Xicotet", true,"Groc", true))



    println("Mostrar tipo y nombre")
    inventario.mostrarAnimales()


    println("Mostrar datos completos")
    inventario.mostrarAnimalesCompleto()

    println("Eliminar Animal Gatorro")
    inventario.eliminarAnimales("Gatorro")
    println(inventario.mostrarAnimales())

    println("Borrar Animales")
    inventario.vaciarInventario()
    println(inventario.mostrarAnimales())


}