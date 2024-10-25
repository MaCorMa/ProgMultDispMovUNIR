class Cliente : Persona{ // : actua como el extends en Java

    constructor(nombre: String)  : super(nombre) {      //con super hereda de Padre

    }

    override fun registrarCenso(localidad: String) {
        println("Te han censado en la {$localidad} como cliente")
    }
}