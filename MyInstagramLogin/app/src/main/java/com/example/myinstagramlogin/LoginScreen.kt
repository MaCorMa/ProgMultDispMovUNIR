package com.example.myinstagramlogin

import android.app.Activity
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(name:String, modifier: Modifier){
    Box(Modifier.fillMaxSize().padding(16.dp)){
        Header(Modifier.align(alignment = TopEnd))
        Body(Modifier.align(alignment = Center))
        //Footer()
    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(imageVector = Icons.Default.Close, contentDescription = "close App",
        modifier = modifier.clickable {activity.finish()})
}

@Composable
fun Body(modifier: Modifier) {

    var email by rememberSaveable { mutableStateOf("asd") }
    var password by rememberSaveable { mutableStateOf("asd") }
    var isLoginEnable by rememberSaveable { mutableStateOf(false) }

    Column (modifier = modifier){
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        Email(email, {email=it})
        Spacer(modifier = Modifier.size(8.dp))
        Password(password, {password=it})
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(8.dp))
        LoginButton(isLoginEnable)

    }
}



/*
@Composable
fun Footer() {
    TODO("Not yet implemented")
}*/


//Content
@Composable
fun ImageLogo(modifier: Modifier){
    Image(painter = painterResource(id=R.drawable.logoari), contentDescription = "logo",
        modifier=modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(email:String, onTextChanged: (String) -> Unit) {
    TextField(value=email, onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(), placeholder = { Text(text="Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent
        )
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(text="Forgot Password", fontSize = 12.sp, fontWeight = FontWeight.Bold,
        color = Color.Red, modifier = modifier)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email:String, onTextChanged: (String) -> Unit) {
    TextField(value=email, onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(), placeholder = { Text(text="Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent
        )
    )
}

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(onClick = {}, enabled = loginEnable, modifier=Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors()){
            Text(text="Log In")
        }
}