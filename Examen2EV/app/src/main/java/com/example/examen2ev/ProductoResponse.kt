package com.example.examen2ev

import com.google.gson.annotations.SerializedName


//clase para mapear el retorno de la API
data class ProductoResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val results: List<Producto>
)

//para sacar valores a pantalla
data class Producto(
    @SerializedName("_id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("price") var price: Double,
    @SerializedName("category") var category: String,
    @SerializedName("image") var image: String,
    @SerializedName("active") var active: Boolean
)
