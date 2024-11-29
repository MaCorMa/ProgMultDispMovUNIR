package com.example.imcapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imcapp.MainActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {


    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescripcion: TextView
    private lateinit var btnCalc: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resultIMC= intent.extras?.getDouble(IMC_KEY)

        initComponents()

        initListener()

        if (resultIMC != null) {
            initUI(resultIMC)
        }
    }

    private fun initUI(resultIMC : Double) {
        this.tvIMC.text=resultIMC.toString()
        this.tvIMC.setTextColor(Color.RED)
    }

    private fun initListener() {
        this.btnCalc.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initComponents() {
        this.tvIMC=findViewById(R.id.tvIMC)
        this.tvResult=findViewById(R.id.tvResult)
        this.btnCalc=findViewById(R.id.btnCalc)

    }


}