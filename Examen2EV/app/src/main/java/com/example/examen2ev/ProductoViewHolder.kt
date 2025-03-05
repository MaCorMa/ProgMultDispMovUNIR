package com.example.examen2ev

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2ev.databinding.ProductoBinding
import com.squareup.picasso.Picasso


class ProductoViewHolder(view: View):RecyclerView.ViewHolder(view) {

    //binding con la clase autogenerada por View Binding
    private val binding = ProductoBinding.bind(view);

    //bindeo de la info a los views
    fun bind(producto: Producto){
        Log.d("Picasso", "Cargando imagen: ${producto.image}")
        Picasso.get().load(producto.image).into(binding.ivProducto)
        binding.tvNombre.text = producto.name
        binding.tvPrecio.text = "${producto.price}€"
        //igual para el resto de información de producto
    }
}