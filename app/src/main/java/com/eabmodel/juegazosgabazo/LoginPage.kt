package com.eabmodel.juegazosgabazo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class LoginPage : AppCompatActivity() {

    val dbController = DBController(this)
    lateinit var loginButton: View
    lateinit var registerText: TextView
    lateinit var username: TextView
    lateinit var password: TextView
    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        Log.d("LIFECYCLE", "onCreate LoginPage")
    dbController.createUser("mauri", "123","mau")
        initViews()

        loginButton?.setOnClickListener {
            if(dbController.verifyUser(username.text.toString(), password.text.toString(), 0)){
                val intent = Intent(this, MainPage::class.java)
                val user = User(username.text.toString(), password.text.toString())
                val userJson = gson.toJson(user)
                intent.putExtra("user",userJson)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        registerText.setOnClickListener{
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }


    }



    fun initViews(){
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        registerText = findViewById(R.id.register)
    }


}