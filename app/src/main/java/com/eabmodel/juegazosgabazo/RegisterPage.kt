package com.eabmodel.juegazosgabazo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class RegisterPage() : AppCompatActivity() {

    var dbController = DBController(this)
    lateinit var registerButton: View
    lateinit var name: TextView
    lateinit var username: TextView
    lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        Log.d("LIFECYCLE", "onCreate LoginPage")
        //dbController.deleteAllUsers()
        initViews()

        //Toast.makeText(this, "${dbController.obtainAllUsers()}", Toast.LENGTH_LONG).show()

        registerButton?.setOnClickListener {
            if (name.text.toString() != "" && username.text.toString() != "" && password.text.toString() != ""){
                if(dbController.createUser(username.text.toString(), password.text.toString(), name.text.toString())){
                    Toast.makeText(this, "User created successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginPage::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "This user already exists!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "There are empty fields!", Toast.LENGTH_SHORT).show()
            }
        }



    }

    fun initViews(){

        name = findViewById(R.id.name)

        username = findViewById(R.id.username)

        password = findViewById(R.id.password)

        registerButton = findViewById(R.id.registerButton)

    }


}