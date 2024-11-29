package com.example.examen_manuelcorrecher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //variables para recoger elementos
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnLogin = findViewById(R.id.btnLogin)
        initListeners()
    }
    private fun initListeners() {
        this.btnLogin.setOnClickListener{
            pasaPantalla()
        }
    }

    private fun pasaPantalla() {
        //se crea el intent
        val intent = Intent(this, InfoLogin::class.java)
        //put Extra para pasar datos
        val user = findViewById<EditText>(R.id.etUser);
        val pass = findViewById<EditText>(R.id.etPass);
        //si no estan vacios, pasa los extra y lanza activity
        if(user.text.isNotEmpty() && pass.text.isNotEmpty()) {
            intent.putExtra("user", user.text.toString())
            intent.putExtra("pass", pass.text.toString())
            //startActivity para lanzar la otra actividad
            startActivity(intent)
        }
    }

}