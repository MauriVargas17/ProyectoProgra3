package com.eabmodel.juegazosgabazo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eabmodel.juegazosgabazo.controllers.DBController
import com.eabmodel.juegazosgabazo.controllers.SPController
import com.google.gson.Gson



class LoginPage : AppCompatActivity() {

    val dbController = DBController(this)
    lateinit var loginButton: View
    lateinit var registerText: TextView
    lateinit var username: TextView
    lateinit var password: TextView
    val gson = Gson()
    lateinit var spController: SPController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        Log.d("LIFECYCLE", "onCreate LoginPage")
        //dbController.createUser("mauri", "123","mau")
        initViews()
       spController = SPController()

        loginButton.setOnClickListener {
            if(dbController.verifyUser(username.text.toString(), password.text.toString(), 0)){

                val intent = Intent(this, Shopwindow::class.java)
                val user = dbController.verifyUser(username.text.toString(), password.text.toString())
                val userJson = gson.toJson(user)
                intent.putExtra("user",userJson)
                spController.saveUser(this, user)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show()
            }
        }

        registerText.setOnClickListener{
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        var spUser = spController.getUser(this)

        if(spUser != null) {

            val intent = Intent(this, Shopwindow::class.java)
            intent.putExtra("user", gson.toJson(spUser))
            startActivity(intent)
        }






    }



    fun initViews(){
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.continueButtonBox)
        registerText = findViewById(R.id.register)
    }


}