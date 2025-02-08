package com.example.actividad2_manuelcorrecher

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class FullRecipe : AppCompatActivity(){
    //variable para usar boton
    private lateinit var btnBack: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.item_full)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //recoger los datos de la MainActivity
        btnBack = findViewById(R.id.btnBack)

        //Se recogen el Meal de la MainActivity
        val meal = intent.getSerializableExtra("meal") as? Meal

        meal?.let {
            val ivRecipeFull = findViewById<ImageView>(R.id.ivRecipe)
            val tvRecipeNameFull = findViewById<TextView>(R.id.tvRecipeName)
            val tvRecipeInstructionsFull = findViewById<TextView>(R.id.tvRecipeInstructions)
            val btnBack = findViewById<ImageButton>(R.id.btnBack)

            Picasso.get().load(it.image).into(ivRecipeFull)
            tvRecipeNameFull.text = it.name
            tvRecipeInstructionsFull.text = it.instruction

            btnBack.setOnClickListener {
                finish() // termina la activity y vuelve a la anterior
            }
        }

    }
}