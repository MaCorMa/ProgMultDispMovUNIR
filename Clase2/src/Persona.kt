class Persona(val nombre:String, var edad:Int) {
    val isMayorEdad
        get() = this.edad>=18

    var sobrePeso = false

    var peso = 0.0
        set(value){         //value es el valor que se le va a dar usando set
            field = value   //field siempre se refiere a la variable
            sobrePeso = value>100
        }
}