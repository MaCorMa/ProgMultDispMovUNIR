package com.example.actividad2_manuelcorrecher

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actividad2_manuelcorrecher.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import java.util.Locale

class Recipes : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityMainBinding

    private lateinit var  adapter: RecipeAdapter

    private val recipeMeals = mutableListOf<Meal>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        this.initRecyclerView()

        val ingredient = intent.getStringExtra("ingredient") ?: "chicken" //si no hay nada, chicken
        this.searchByIngredient(ingredient)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnBack = findViewById<ImageButton>(R.id.btnBack) //para volver

        btnBack.setOnClickListener {
            finish() // termina la activity y vuelve a la anterior
        }
    }

    private fun initRecyclerView() {
        adapter = RecipeAdapter(recipeMeals)
        binding.rvRecipes.layoutManager = GridLayoutManager(this,2)
        binding.rvRecipes.adapter = adapter
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByIngredient(ingredientQuery: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<RecipeResponse> = getRetrofit()
                .create(ApiService::class.java)
                .getRecipeByIngredient(ingredientQuery)

            val meals: RecipeResponse? = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val mealList = call.body()?.meals ?: emptyList() //coge el listado de platos de la API y asegura que no es null
                    recipeMeals.clear()
                    recipeMeals.addAll(mealList)
                    adapter.notifyDataSetChanged()

                    recipeMeals.take(1).forEachIndexed { index, meal -> //limitado a 3 para no pasar del limite de llamadas a la APÌ
                        searchById(meal.recipeId, index)
                    }
                } else {
                    showError()
                }
            }
        }
    }

    private fun searchById(mealId: String, index: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<RecipeResponse> = getRetrofit()
                .create(ApiService::class.java)
                .getRecipeById(mealId)

            runOnUiThread {
                if (call.isSuccessful) {
                    val fullMeal = call.body()?.meals?.firstOrNull() // lookup.php devuelve solo uno, cogemos sólo el primero
                    if (fullMeal != null) {
                        recipeMeals[index].instruction = fullMeal.instruction // añade las instrucciones al Meal indexado
                        adapter.notifyItemChanged(index)
                    }
                } else {
                    showError()
                }
            }
        }
    }

    fun onMealClick(meal: Meal) {
        val intent = Intent(this, FullRecipe::class.java)
        intent.putExtra("meal", meal)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            this.searchByIngredient(query.lowercase(Locale.getDefault()))
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true //para que vaya filtrando y recargando según se escribe
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error en la petición", Toast.LENGTH_SHORT).show()
    }
}