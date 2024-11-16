package com.example.act1mcorrecher

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.calcButton).setOnClickListener{
            //Recoger valores de los campos
            val brutoAnual = findViewById<EditText>(R.id.brutoAnual)
            val nrPagas = findViewById<Spinner>(R.id.spPagas)
            val edad = findViewById<EditText>(R.id.edad)
            val grProf = findViewById<Spinner>(R.id.spGrupoProf)
            val gradDisc = findViewById<Spinner>(R.id.spGradoDisc)
            val estadoCivil = findViewById<Spinner>(R.id.spEstadoCivil)
            val nrHijos = findViewById<EditText>(R.id.nrHijos)
            //Comprobar si los campos están vacíos
                if(brutoAnual.text.isBlank() ||
                    nrPagas.selectedItem == null ||
                    edad.text.isBlank() ||
                    grProf.selectedItem == null ||
                    gradDisc.selectedItem == null ||
                    estadoCivil.selectedItem == null ||
                    nrHijos.text.isBlank()) {
                        Toast.makeText(this, "Por favor, rellena todos los campos!",Toast.LENGTH_SHORT).show()
                    } else{
                    //Para testeo
                    /*
                    Log.i("Bruto", brutoAnual.text.toString())
                    Log.i("Pagas", nrPagas.toString())
                    Log.i("Edad", edad.text.toString())
                    Log.i("Grupo prof", grProf.selectedItem.toString())
                    Log.i("Grado Disc", gradDisc.selectedItem.toString())
                    Log.i("Estado Civ", estadoCivil.selectedItem.toString())
                    Log.i("Nr Hijos", nrHijos.text.toString())
                     */

                    //Navegar a la siguiente view -> Intent
                    val intent = Intent(this, Resultados::class.java)
                    //Enviar valores
                    intent.putExtra("brutoAnual", brutoAnual.text.toString().toDouble())
                    intent.putExtra("nrPagas", nrPagas.selectedItem.toString())
                    intent.putExtra("edad", edad.text.toString().toInt())
                    intent.putExtra("grProf", grProf.selectedItem.toString())
                    intent.putExtra("grDisc", gradDisc.selectedItem.toString())
                    intent.putExtra("estadoCivil", estadoCivil.selectedItem.toString())
                    intent.putExtra("nrHijos", nrHijos.text.toString().toInt())

                    //Iniciar la siguiente activity
                    startActivity(intent)
                }
            }
        //Popular spinners
            //Pagas
        val spPagas = findViewById<Spinner>(R.id.spPagas)
        val adapterPagas = ArrayAdapter.createFromResource(this, R.array.aryPagas ,android.R.layout.simple_spinner_item)
        adapterPagas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPagas.adapter = adapterPagas
            //Grupo Profesional
        val spGrProf = findViewById<Spinner>(R.id.spGrupoProf)
        val adapterGrProf = ArrayAdapter.createFromResource(this, R.array.arygrProf ,android.R.layout.simple_spinner_item)
        adapterGrProf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGrProf.adapter = adapterGrProf
            //Grado Discapacidad
        val spGrDisc = findViewById<Spinner>(R.id.spGradoDisc)
        val adapterGrDisc = ArrayAdapter.createFromResource(this, R.array.aryDisc ,android.R.layout.simple_spinner_item)
        adapterGrDisc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spGrDisc.adapter = adapterGrDisc
            //Estado civil
        val spEstadoCiv = findViewById<Spinner>(R.id.spEstadoCivil)
        val adapterEstadoCiv = ArrayAdapter.createFromResource(this, R.array.aryEstadoCiv ,android.R.layout.simple_spinner_item)
        adapterEstadoCiv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spEstadoCiv.adapter = adapterEstadoCiv
        }
    }