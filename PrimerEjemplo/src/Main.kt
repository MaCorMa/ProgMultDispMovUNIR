fun main(){

    //operadores()
    //square(2)
    //parametrosNombrados()
    cuando()
}

fun operadores(){
    val name = "Kotlin"
    val num = 23.0
    val cons = 45
    val suma : Double //el tipo de dato se pone en mayúscula
    //c=45 → val se usa para constantes, no se puede modificar una vez declarada

    val textoPrueba = """
        Con las tres comillas
        se puede guardar un string con múltiples 
        saltos de línea
    """

    println("Hola, "+name+"!")
    println(textoPrueba)

    //var indice : Int
    //indice = null → Int no es un elemento nullable, para hacerlo
    var indiceNu : Double? // con el ? lo hacemos nullable
    indiceNu = null

    var a = 21
    var c: Int? = null
    var division = a / (c ?: 3) //operador elvis (?:) se utiliza al declarar una variable como nulable, ya que antes de usarse se comprueba si tiene valor para evitar NullPointerException
    //si es nulo, cogerá el valor 1 (valor a la derecha del operador elvis)
    println("primera division "+division)
    c=7
    division = a/(c ?: 1) //el IntelliJ ya marca que no va a tomar valor del operando elvis ya que en la línea de arriba se le da un valor
    println("Seguinda diivision "+division)

    //Utilizaremos operadores especiales de Kotlin de tal forma que si no se intertan
    val calificacion = readLine()?.toDoubleOrNull()?:return //lee por pantalla, fuerza que sea double o null, si es null para la funcion main con el return
    println(calificacion)//Si no se le da ningún valor, para programa sin imprimir nada

    //OPERADORES DE CONTROL

    //for
    for(i in 1..5){
        println("Contando $i")
    }
    //en java
    //for(int i=0; i<5, i++){
    //}

    for(char in 'a'..'k'){
        print(char)
    }
    for(char in 'k' downTo 'a'){ //downTo para hacer decreciente
        print(char)
    }
    for(char in 'a'..'k' step 2){ //step para definir el paso
        print(char)
    }
    for(char in 'a' until 'k'){ //eliminar el limite superior
        print(char)
    }
    //para recorrer un indice
    val nombres = arrayOf("Manuel", "pepe", "carlos")
    for (i in nombres.indices){
        println("[$i, ${nombres[i]}]")
    }
}


//funciones
// identificador de funcion, el fun
// lista de parametros y tipo de retorno
// cuerpo de la función
fun square(x:Int): Int{   //también se podría expresar como → fun square(x:Int) = x * x
    return x * x
}

fun saludar (nombre:String): Unit{ //unit para indicar que la función no retorna ningún valor
    println(nombre)
}

fun suma (a:Int = 11, b:Int = 2) = a+b //se ha usado la forma de cuerpo mínimo explicada en fun square

fun parametrosNombrados(){
    //Funcion parámetros nombrados
    val sum = suma(a=12, b=27)
    println(sum)
}

fun cuando(){
    val a = 5
    when{
        a > 0 ->{
            print("Es positivo")
            println("Prueba")
            val c = 0
            println(c)
        }a==0 -> print("Es cero")
        else -> print("Es negativo")
    }
}
