import java.util.*

class Coche(matricula: String, marca: String, acabo:String){
    //Constructor primario

    val matricula: String
    val marca: String
    var acabo: String
    var bastidor: String

    init{ //Para expandir la inicialización de variables con el bloque init
        this.matricula = matricula
        this.marca = marca
        this.acabo = acabo
        this.bastidor = UUID.randomUUID().toString()
        //Validar cualquier cosa

    }



    //Para poder usar el toString
    override fun toString(): String {
        return "Coche(matricula='$matricula', marca='$marca')"
    }

    //constructor secundario
    constructor(matricula: String, marca: String, acabo:String, bastidor: String):this(matricula, marca, acabo){
                 this.bastidor = bastidor
    }
}