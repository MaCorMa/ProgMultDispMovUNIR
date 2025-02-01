package com.example.ejemploretrofitclase


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getDogsByBreed(@Url url: String) : Response<DogsResponse>

    @GET
    suspend fun getAllBreeds(@Url url: String) : Response<DogsResponse>
    //definir tantas peticiones como se necesiten

    //getDogById(id)

    //@POST

    //@PUT

    //@DELETE

}