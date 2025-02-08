package com.example.actividad2_manuelcorrecher

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter (private val meals:List<Meal>) : RecyclerView.Adapter<RecipeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipeViewHolder(layoutInflater.inflate(R.layout.item_recipe, parent, false))
    }

    override fun getItemCount(): Int {
        return this.meals.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val meal = this.meals[position]
        holder.bind(meal) //holder bindea el elemento que recoge por el int posicion
    }
}