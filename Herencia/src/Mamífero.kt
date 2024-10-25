interface Mamífero {

    val p1: Int //Propiedad abstracto
    val p2: Boolean // Porpiedad regular con accesor
        get()=p1>0

    fun m1() // método abstracto

    fun m2(){ //método regular
        println("Método implementado")
    }
}