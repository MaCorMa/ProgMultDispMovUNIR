
fun main(){
    println("Bienvenido a DAMBANK")


    //Variables para CuentaBancaria
    println("Introduzca IBAN (formato: 2 letras y 22 dígitos)")
    val iban = readLine().orEmpty()
    println("Introduzca titular")
    val titular = readLine().orEmpty()


    //Para los throws de CuentaBancaria
    try {
        val cuentaBancaria = CuentaBancaria(iban, titular)
        println("Cuenta creada correctamente")

        var opcion: Int
        do{
            println("""
                Elige una opción:
                1. Ver datos de la cuenta
                2. Ver saldo
                3. Realizar un ingreso
                4. Realizar una retirada
                5. Ver movimientos
                6. Salir
            """.trimIndent()) //trimIndent borra espacios de más para mejorar formato
           opcion = readLine()?.toIntOrNull() ?: 0

            when(opcion){
                1 -> println(cuentaBancaria.obtenerDatosCuenta())
                2 -> println(cuentaBancaria.obtenerSaldo())
                3 -> {
                    println("Introduzca la cantidad a ingresar")
                    val cantidad = readLine()?.toDoubleOrNull() ?: 0.0
                    cuentaBancaria.ingresarDinero(cantidad)
                }
                4 ->  {
                    println("Introduzca la cantidad a retirar")
                    val cantidad = readLine()?.toDoubleOrNull() ?: 0.0
                    cuentaBancaria.retirarDinero(cantidad)
                }
                5 -> cuentaBancaria.mostrarMovimientos()
                6 -> println("Gracias por usar DAMBank")
                else -> println("Opción no válida")
            }

        } while (opcion != 6)

    } catch (e: IllegalArgumentException){
        println(e.message)
    }

}