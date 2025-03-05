package com.example.examen2ev

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductoApiService {


    //lamada a la API, devuelve dataclass creada ProductoResponse
    //suspend para petición asincrona
    @GET
    suspend fun getProducts(@Url url: String) : Response<ProductoResponse>

}