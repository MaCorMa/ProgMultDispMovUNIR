class CuentaBancaria(val iban:String, val titular: String) {

    private var saldo: Double = 0.0 //privada para poder modificar el saldo con movimientos desde la clase pero no pueda modificarse desde fuera
    private var idMovimiento:Int = 0
    private val movimientos: MutableList<Movimiento> = mutableListOf() 

    init{
        if(!validarIban(iban)){
            throw IllegalArgumentException("El IBAN no es válido")
        }
    }

    //Validar Titular
    private fun validarTitular(titular:String):Boolean{
        var isOk = false
        if(titular.length>=3){
            isOk=true
        }
        return isOk
    }

    fun obtenerSaldo(): Double{
        return this.saldo
    }

    //función validar formato iban
    private fun validarIban(iban:String):Boolean{
        //"[A-Z]{2}[0-9]{22}"
        return iban.matches(Regex("[A-Z]{2}[0-9]{22}"))
    }


}