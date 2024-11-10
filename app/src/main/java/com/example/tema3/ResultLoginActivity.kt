package com.example.tema3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.clase1.R

class ResultLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Recoger textView
        val resultTV = findViewById<TextView>(R.id.resultTVLogin)

        //Recoer los putExtra de la activity anterior
        val user: String = intent.extras?.getString("user").orEmpty()
        val password: String = intent.extras?.getString("password").orEmpty()

        //Modificar el contenido del resultTV
        resultTV.text= "Se ha logueado correctamente \n Usuario:$user \n Contraseña: $password"
    }
}