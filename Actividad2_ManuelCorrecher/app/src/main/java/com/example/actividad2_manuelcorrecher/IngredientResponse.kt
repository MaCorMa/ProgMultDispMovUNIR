package com.example.actividad2_manuelcorrecher

import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    val meals: List<Ingredient>
)

data class Ingredient(
    @SerializedName("strIngredient") val name: String
)