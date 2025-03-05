package com.example.examen2ev

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen2ev.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    //binding
    private lateinit var binding : ActivityMainBinding
    //adapter
    private lateinit var adapter: ProductoAdapter
    //lista productos
    private val listaProductos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //asignar el binding, para poder acceder a los elementos
        binding = ActivityMainBinding.inflate(layoutInflater)

        //al hacer el binding se puede acceder directamente
        setContentView(binding.root)//setContentView(R.layout.activity_main)

        this.initRecyclerView()
        //llamada a la función para petición a la api
        this.fetchData("products")





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //asignar el adapter al recyclerview
    private fun initRecyclerView() {
        adapter = ProductoAdapter(listaProductos)
        binding.rvProductos.layoutManager = LinearLayoutManager(this)//para que use su propio layout
        binding.rvProductos.adapter = adapter //le pasamos el adapter
    }

    private fun getRetrofit() : Retrofit{
        //configurar elemento Retrofit
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/api/") //url de la api
            .addConverterFactory(GsonConverterFactory.create()) //para serializar gson
            .build()
    }
    //funcion llamada asincrona con corrutinas
    private fun fetchData(query: String){
        CoroutineScope(Dispatchers.IO).launch { //corrutina IO para dispatcher entrada salida
            val call:Response<ProductoResponse> = getRetrofit() //objeto retrofit
                .create(ProductoApiService::class.java).getProducts("$query") //llamada al metodo

            val pagProductos: ProductoResponse? = call.body() //mete el response en pagProductos

            //para pintar en la interfaz
            runOnUiThread{
                if(call.isSuccessful){ //si la llamada es OK
                    if (pagProductos!=null){ //si la llamada no devuelve null
                        listaProductos.clear()
                        listaProductos.addAll(pagProductos.results)
                        adapter.notifyDataSetChanged()//notifica al adapter que han cambiado datos
                        for(producto : Producto in pagProductos.results){ //recorre la lista de productos del ProductoResponse
                            //saca la info del producto por el log
                            Log.v("Query API", producto.id)
                            Log.v("Query API", producto.name)
                            Log.v("Query API", producto.category)
                            Log.v("Query API", producto.description)
                            Log.v("Query API", producto.image)
                        }
                    }
                }else{
                    showError()
                    //Log.e("Query API", "Error en la petición")
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this,"Error en la petición",Toast.LENGTH_SHORT).show()
    }
}