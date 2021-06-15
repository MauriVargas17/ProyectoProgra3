package com.eabmodel.juegazosgabazo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class Shopwindow: AppCompatActivity() {

    val gson = Gson()
    lateinit var bag: ImageView
    lateinit var interactions: ImageView
    lateinit var profile: ImageView
    lateinit var plus: ImageView
    lateinit var cart: ImageView
    lateinit var searchBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopwindow_page)
        Log.d("LIFECYCLE", "onCreate Shopwindow")
    init()
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        Toast.makeText(this, " ${user.username}, what are you buying today?", Toast.LENGTH_SHORT).show()

        bag.setOnClickListener{


        }

        profile.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
        }
        interactions.setOnClickListener{
            val intent = Intent(this, Interactions::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
        }



    }

    fun init(){
        bag = findViewById(R.id.bag)
        interactions = findViewById(R.id.interactions)
        profile = findViewById(R.id.profile)
        plus = findViewById(R.id.plus)
        cart = findViewById(R.id.cart)
        searchBar = findViewById(R.id.search)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart MainPage")
    }


}