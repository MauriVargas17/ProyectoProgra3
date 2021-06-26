package com.eabmodel.juegazosgabazo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.eabmodel.juegazosgabazo.controllers.DBController
import com.eabmodel.juegazosgabazo.objects.User
import com.google.gson.Gson

class Settings : AppCompatActivity() {

    val dbController = DBController(this)
    val gson = Gson()
    lateinit var dewit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        dewit = findViewById(R.id.dewit)

        dewit.setOnClickListener {
            Log.d("Settings", "${user.password}")
            dbController.changePassword(user.username, user.password, "29")
            Log.d("Settings", "${user.password}")
            Toast.makeText(this, "password changed", Toast.LENGTH_SHORT).show()

        }


    }
}