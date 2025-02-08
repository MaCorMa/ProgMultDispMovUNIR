package com.example.ejemploretrofitclase

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemploretrofitclase.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view) //layout particular que hemos creado

    fun bind(image: String){

        Picasso.get().load(image).into(binding.ivDog) //resto de elementos irian aquí

    }



}