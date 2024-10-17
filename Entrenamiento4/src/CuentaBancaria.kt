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

    //Ingresar, retirar y ver movimientos

    fun retirarDinero(cantidad : Double){
        if(cantidad>0.0){
            if(this.saldo - cantidad >= -50) {
                this.saldo -= cantidad
                registrarMovimiento(cantidad,"Retirada")
            }
            else{
                println("No se puede retirar la cantidad solicitada. Saldo no puede ser menor a -50€")
            }
        }
        else{
            println("Cantidad debe ser positiva")

        }
    }
    //crear movimiento
    private fun registrarMovimiento(cantidad: Double, tipo:String) {
        val mov = Movimiento(this.idMovimiento, tipo, cantidad)
        this.movimientos.add(mov)
        this.idMovimiento++
    }

    fun ingresarDinero(cantidad : Double){
        if(cantidad>0.0){
            this.saldo += cantidad
            registrarMovimiento(cantidad, "Ingreso")
        }
        else{
            println("Cantidad debe ser positiva")
        }
    }

    //Mostrar movimientos
    fun mostrarMovimientos(){
        if(movimientos.isEmpty()){
            println("No hay movimientos registrados")
        }
        else{
            this.movimientos.forEach{println(it)}
        }
    }

    //ver datos de la cuenta
    fun obtenerDatosCuenta(): String{
        return "Datos de la cuenta: IBAN ${this.iban} Titular: ${this.titular} Saldo: ${this.saldo}"
    }


}