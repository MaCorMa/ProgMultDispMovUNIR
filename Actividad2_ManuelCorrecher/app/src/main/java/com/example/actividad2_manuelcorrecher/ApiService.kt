package com.example.actividad2_manuelcorrecher

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("list.php")
    suspend fun getIngredients(@Query("i") choice:String):Response<IngredientsResponse>
    @GET("filter.php")
    suspend fun getRecipeByIngredient(@Query("i") ingredient:String) : Response<RecipeResponse>
    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") id:String) : Response<RecipeResponse>
}