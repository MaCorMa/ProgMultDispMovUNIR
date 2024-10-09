

fun main(){

    var micoche = Coche("0180HPT", "FIAT", "Style")
   // print(micoche.toString())

    var coche2 = Coche("0180HPT", "FIAT", "Style")
    val coche3 = Coche("0180HPT", "FIAT", "Style", "JHHSDUI9898")

    println("Coche2: "+coche2)
    println("Coche2: "+coche2.bastidor)
    println("Coche3: "+coche3.bastidor)

    val pp = Persona ("Pepe", 42)
    pp.peso = 105.25

    println("¿Sobrepeso: ?:${if (pp.sobrePeso) "Si" else "No"}")
    println(pp.peso)
}