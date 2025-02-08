package com.example.actividad2_manuelcorrecher

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad2_manuelcorrecher.databinding.ItemRecipeBinding
import com.squareup.picasso.Picasso

class RecipeViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemRecipeBinding.bind(view)

    fun bind(meal:Meal){
        Picasso.get().load(meal.image).into(binding.ivRecipe)
        binding.tvRecipeName.text=meal.name

        itemView.setOnClickListener {
            // Use the context from the itemView to launch FullRecipe activity
            val context = itemView.context
            val intent = Intent(context, FullRecipe::class.java)
            intent.putExtra("meal", meal)
            context.startActivity(intent)
        }
    }
}