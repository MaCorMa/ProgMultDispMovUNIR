package com.example.examen2ev

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter (private val results: List<Producto>) : RecyclerView.Adapter<ProductoViewHolder>(){
    //de la api nos viene una lista con los productos
    //En el viewholder se definen los bindings para poder plasmarlo/llamarlo en el adapter


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        //convierte el xml en objeto view, se le da el recyclerview como contexto
        val layoutInflater = LayoutInflater.from(parent.context)
        //convierte el producto.xml en una view, hace return de una nueva instancia de ProductoViewHolder con la view
        return ProductoViewHolder(layoutInflater.inflate(R.layout.producto,parent,false))
    }

    override fun getItemCount(): Int {
        return this.results.size //devuelve el número de items
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val item = this.results[position] //usa el elemento de la posición
        holder.bind(item) //usa el elemento sacado de la posición
    }
}