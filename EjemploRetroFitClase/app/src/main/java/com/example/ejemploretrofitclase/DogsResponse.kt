package com.example.ejemploretrofitclase

import com.google.gson.annotations.SerializedName

data class DogsResponse (
    @SerializedName("status") var status: String,
    @SerializedName("message") var imagenes: List<String>
)