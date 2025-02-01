package com.example.ejemploconstraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ejemploconstraintlayout.ui.theme.EjemploConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploConstraintLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyConstraintLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyConstraintLayout(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier=Modifier.fillMaxSize()) {

        val(boxRed,boxBlue,boxGreen,boxMagenta,boxGray,boxCyan)= createRefs()

        Box(modifier = Modifier.size(125.dp)
            .background(Color.Red)
            .constrainAs(boxRed){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        Box(modifier = Modifier.size(125.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue){
                bottom.linkTo(boxRed.top)
                start.linkTo(boxRed.end)
            })
        Box(modifier = Modifier.size(125.dp)
            .background(Color.Green)
            .constrainAs(boxGreen){
                bottom.linkTo(boxRed.top)
                end.linkTo(boxRed.start)
            })
        Box(modifier = Modifier.size(125.dp)
            .background(Color.Magenta)
            .constrainAs(boxMagenta){
                top.linkTo(boxRed.bottom)
                start.linkTo(boxRed.end)
            })
        Box(modifier = Modifier.size(125.dp)
            .background(Color.Gray)
            .constrainAs(boxGray){
                top.linkTo(boxRed.bottom)
                end.linkTo(boxRed.start)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploConstraintLayoutTheme {
        MyConstraintLayout()
    }
}