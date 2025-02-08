package com.example.actividad2_manuelcorrecher

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeResponse(
    val meals: List<Meal>
)


data class Meal(
    @SerializedName("idMeal") var recipeId: String,
    @SerializedName("strMealThumb") var image: String,
    @SerializedName("strMeal") var name:String,
    @SerializedName("strInstructions") var instruction:String
):Serializable
