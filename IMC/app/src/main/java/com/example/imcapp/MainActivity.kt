package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    //Variables privadas para recoger elementos visuales, inicialización tardía
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnImcCalculator: Button


    //Crear atributos necesarios para logica de los componentes
    private var isMaleSelected : Boolean = true
    private var isFemaleSelected : Boolean = false
    private var currentWeight : Int = 45
    private var currentAge : Int = 23
    private var currentHeight : Double = 1.7


    //Creacion de un companion object accesible desde todas las activities
    companion object{
        const val IMC_KEY = ("IMC_RESULT")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Codigo en el OnCreate

        //Iniciar componentes visuales
        initComponents()

        //Iniciar listeners de los eventos
        initListeners()

        //Configuraciones visuales
        initUI()
    }

    private fun initListeners() {
        this.viewMale.setOnClickListener{
            if(!this.isMaleSelected){
                this.changeGender()
                this.setGenderColor()
            }
        }
        this.viewFemale.setOnClickListener{
            if(!this.isFemaleSelected){
                this.changeGender()
                this.setGenderColor()
            }
        }
        this.rsHeight.addOnChangeListener{_,value,_ ->
            this.currentHeight=(value/100.0)
            this.tvHeight.text = this.currentHeight.toString()+" cm"
            //definir formato
            val df = DecimalFormat("#,##")
            val result = df.format(value)
        }
        this.btnSubWeight.setOnClickListener{
            this.currentWeight -=1
            setWeight()
        }
        this.btnPlusWeight.setOnClickListener{
            this.currentWeight +=1
            setWeight()
        }
        this.btnSubAge.setOnClickListener{
            this.currentAge -=1
            setAge()
        }
        this.btnPlusAge.setOnClickListener{
            this.currentAge +=1
            setAge()
        }
        this.btnImcCalculator.setOnClickListener{
            val resultIMC = calculateIMC()
            //navegación
            navigateToResult(resultIMC)
        }
    }

    private fun navigateToResult(resultIMC: Double) {
        //creamos el objeto intent
        val intent = Intent(this, ResultIMCActivity::class.java)
        //añadir el extra para pasar el resultIMC
        intent.putExtra(IMC_KEY, resultIMC)
        this.startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val imc = this.currentWeight/(this.currentHeight + this.currentHeight)
        val df = DecimalFormat("#,##")
        val result = df.format(imc).toDouble()
        Log.i("IMC", "El imc es $result")
        return result
    }

    private fun setAge() {
        this.tvAge.text=this.currentAge.toString()
    }

    private fun setWeight() {
        this.tvWeight.text = this.currentWeight.toString()
    }

    private fun initUI() {
        this.setWeight()
        this.setAge()
        this.setGenderColor()
    }

    private fun initComponents(){
        this.viewMale = findViewById(R.id.viewMale)
        this.viewFemale = findViewById(R.id.viewFemale)
        this.tvHeight = findViewById(R.id.tvHeight)
        this.rsHeight = findViewById(R.id.rsHeight)
        this.btnSubWeight = findViewById(R.id.btnSubWeight)
        this.btnPlusWeight = findViewById(R.id.btnPlusWeight)
        this.tvWeight = findViewById(R.id.tvWeight)
        this.btnSubAge = findViewById(R.id.btnSubAge)
        this.btnPlusAge = findViewById(R.id.btnPlusAge)
        this.tvAge = findViewById(R.id.tvAge)
        this.btnImcCalculator = findViewById(R.id.btnCalc)
    }

    private fun changeGender(){
        this.isMaleSelected = !this.isMaleSelected
        this.isFemaleSelected = !this.isFemaleSelected
    }

    private fun setGenderColor(){
        this.viewMale.setCardBackgroundColor(this.getBackGroundColor(this.isMaleSelected))
        this.viewFemale.setCardBackgroundColor(this.getBackGroundColor(this.isFemaleSelected))

    }

    private fun getBackGroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

}

