package com.example.actividad2_manuelcorrecher

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.actividad2_manuelcorrecher.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.ingredients)

        this.fetchIngredients()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun fetchIngredients() {
        CoroutineScope(Dispatchers.IO).launch {
                val response = getRetrofit().create(ApiService::class.java).getIngredients("list")
                runOnUiThread {
                    if (response.isSuccessful) {
                        val ingredients = response.body()?.meals ?: emptyList()
                        createIngredientButtons(ingredients)
                    } else {
                        showError()
                    }
                }
        }
    }


    private fun createIngredientButtons(ingredients: List<Ingredient>) {
        //contenedor grid
        val container = findViewById<GridLayout>(R.id.btnContainer)

        val widthPx = (112 * resources.displayMetrics.density).toInt()  //200dp
        val heightPx = (60 * resources.displayMetrics.density).toInt() //160dp
        val marginPx = (8 * resources.displayMetrics.density).toInt()

        // Se crea un boton por ingrediente, max 10
        ingredients.take(21).forEach { ingredient ->
            val button = Button(this).apply {
                text = ingredient.name.lowercase()
            }

            val params = GridLayout.LayoutParams().apply {
                width = widthPx
                height = heightPx
                setMargins(marginPx,marginPx,marginPx,marginPx)
                setGravity(android.view.Gravity.CENTER)
            }
            button.layoutParams = params

            button.setOnClickListener {
                // envia nombre para la query de recetas
                val intent = Intent(this, Recipes::class.java)
                intent.putExtra("ingredient", ingredient.name)
                startActivity(intent)
            }
            container.addView(button)
        }
    }
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error en la petición", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            this.fetchIngredients()
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true //para que vaya filtrando y recargando según se escribe
    }
}