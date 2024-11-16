package com.example.act1mcorrecher

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Resultados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Recoger valores de la view anterior
        val brutoAnual = intent.extras?.getDouble("brutoAnual")
        val nrPagas = intent.extras?.getString("nrPagas")?.toInt()
        val edad = intent.extras?.getInt("edad")
        val grProf = intent.extras?.getString("grProf")
        val gradDisc = intent.extras?.getString("grDisc")
        val estadoCivil = intent.extras?.getString("estadoCivil")
        val nrHijos = intent.extras?.getInt("nrHijos")

        //Valorar spinners
        fun valorarGrProf(grProf: String?) : Double{
            var grProfPor = 0.0
            when(grProf){
                "Grupo 1: Ingenieros, licenciados y personal de alta dirección." ->grProfPor =0.1
                "Grupo 2: Ingenieros técnicos, Peritos y Ayudantes titulados." -> grProfPor = 0.09
                "Grupo 3: Jefes Administrativos y de Taller." -> grProfPor = 0.08
                "Grupo 4: Ayudantes sin titulación." ->grProfPor =0.07
                "Grupo 5: Oficiales administrativos." ->grProfPor =0.06
                "Grupo 6: Subalternos." ->grProfPor =1.0
                "Grupo 7: Auxiliares administrativos." -> grProfPor =0.05
                "Grupo 8: Oficiales de primera y segunda." -> grProfPor =0.04
                "Grupo 9: Oficiales de tercera y especialistas." ->grProfPor =0.03
                "Grupo 10: Peones." ->grProfPor =0.02
                "Grupo 11: Trabajadores menores de 18 años." ->grProfPor =0.01
                else -> grProfPor =0.0
            }
            return grProfPor
        }
        //Cálculos
            //Deducciones
        fun calcDeducciones(nrHijos:Int, estadoCivil:String, grDisc:String):Int {
            var deducciones = 5000// Mínimo personal base
            when(estadoCivil){
                "Casado" -> deducciones += 1500 * nrHijos
                "Viudo" -> deducciones = 6500
                else -> deducciones = 5000
            }
            when(gradDisc){
                "Menor que 65%" -> deducciones += 3000
                "Mayor que 65%" -> deducciones += 6000
            }
            return deducciones
        }
            //Cotizaciones SS
        fun calcularCotizacionesSS(sueldoBruto: Double): Double {
            // Porcentajes de cotización
            val porcentajeContingenciasComunes = 0.047
            val porcentajeGrupo = valorarGrProf(grProf)
            val porcentajeFormacionProfesional = 0.001
            // Cálculo de las cotizaciones
            val cotizacionContingenciasComunes = sueldoBruto * porcentajeContingenciasComunes
            val cotizacionGrupo = sueldoBruto * porcentajeGrupo
            val cotizacionFormacionProfesional = sueldoBruto * porcentajeFormacionProfesional
            // Suma total de cotizaciones
            return cotizacionContingenciasComunes + cotizacionGrupo + cotizacionFormacionProfesional
        }
            //IRPF
        fun calcularIRPF(brutoAnual: Double): Pair<Double, Double> {
            // Definición de tramos y porcentajes
            val tramos = listOf(
                Pair(12450.0, 0.19),
                Pair(20200.0 - 12450.0, 0.24),
                Pair(35200.0 - 20200.0, 0.30),
                Pair(60000.0 - 35200.0, 0.37),
                Pair(Double.MAX_VALUE, 0.47) // Para ingresos superiores a 60,000
            )
            var impuestoTotal = 0.0
            var baseImponible = brutoAnual - calcularCotizacionesSS(brutoAnual)
            // Calcular el impuesto por tramos
            for ((limite, porcentaje) in tramos) {
                if (baseImponible > limite) {
                    impuestoTotal += limite * porcentaje
                    baseImponible -= limite
                } else {
                    impuestoTotal += baseImponible * porcentaje
                    break
                }
            }
            // Restar deducciones
            val impuestoNeto = (impuestoTotal - calcDeducciones(nrHijos!!,estadoCivil!!,gradDisc!!))
            // Tipo efectivo de IRPF
            val tipoEfectivo = impuestoNeto / brutoAnual
            // Retornar el impuesto neto y el tipo efectivo
            return Pair(impuestoNeto, tipoEfectivo)
        }
        //Llevar a layout
            //crear variables
        val resultNeto = findViewById<TextView>(R.id.resultado_neto)
        val resultPagas = findViewById<TextView>(R.id.resultado_pagas)
        val resultNetoAnual = findViewById<TextView>(R.id.resultado_netoAnual)
        val resultIRPF = findViewById<TextView>(R.id.resultado_IRPF)
        val resultTipo = findViewById<TextView>(R.id.resultado_tipo)
        val resultSS = findViewById<TextView>(R.id.resultado_SS)

        //modificar contenido
            //IRPF
        val sueldoNeto = (brutoAnual?.minus(calcularIRPF(brutoAnual!!).first))
        val sueldoNetoMes = sueldoNeto!!/nrPagas!!
        resultNeto.text = "$sueldoNetoMes €"
            //Pagas extras
        val valorPagas = (sueldoNeto -(sueldoNetoMes*12))/nrPagas
        resultPagas.text = "$valorPagas €"
            //Neto anual
        resultNetoAnual.text = "$sueldoNeto €"
            //Retención IRPF
         val valorIRPF = calcularIRPF(brutoAnual!!).first
        resultIRPF.text = "$valorIRPF €"
            //tipo IRPF
        val tipoIRPF = calcularIRPF(brutoAnual!!).second
        resultTipo.text = "$tipoIRPF %"
            //Cuotas SS
        val valorSS = calcularCotizacionesSS(brutoAnual)
        resultSS.text = "$valorSS €"


        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.volverButton).setOnClickListener(){
            //Navegar al view anterior
            //Navegar a la siguiente view -> Intent
            val intent = Intent(this, MainActivity::class.java)
            //Iniciar la siguiente activity
            startActivity(intent)
        }
    }


}