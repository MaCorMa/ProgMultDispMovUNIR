package com.example.examen_manuelcorrecher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InfoLogin : AppCompatActivity() {
    //variable para usar boton
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recoger los resultsets de la activity InfoLogin
        btnBack = findViewById(R.id.btnBack)
        val tvUserLog = findViewById<TextView>(R.id.tvUserLog)
        val tvPassLog = findViewById<TextView>(R.id.tvPasswordLog)
        //Se recogen los extras
        val user: String = intent.extras?.getString("user").orEmpty()
        val pass: String = intent.extras?.getString("pass").orEmpty()

        //modificar el texto de los tv InfoLogin
        tvUserLog.text=user
        tvPassLog.text=pass

        initListener()
    }
    private fun initListener() {
        this.btnBack.setOnClickListener{
            pasaPantalla()
        }
    }

    private fun pasaPantalla() {
        //se crea el intent
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}