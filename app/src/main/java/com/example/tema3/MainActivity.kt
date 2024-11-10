package com.example.tema3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.clase1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.loginButton).setOnClickListener{
                val usuario = findViewById<EditText>(R.id.emailHint)
                val password = findViewById<EditText>(R.id.passwordHint)

                if(usuario.text.isNotEmpty() && password.text.isNotEmpty()){
                    Log.i("Login", usuario.text.toString())
                    Log.i("Login", password.text.toString())

                    //Navegar a la siguiente view → intent
                    val intent = Intent(this, ResultLoginActivity::class.java)

                    //Añadir los valores para pasar a la otra activity
                    intent.putExtra("user", usuario.text.toString())
                    intent.putExtra("password", password.text.toString())


                    //Iniciamos otra activity
                    startActivity(intent)
                } else{
                    usuario.setTextColor(Color.RED)
                    usuario.setText("Debe rellenar este campo")
                }
        }
    }
}