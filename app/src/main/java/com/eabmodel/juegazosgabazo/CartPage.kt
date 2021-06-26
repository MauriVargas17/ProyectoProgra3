package com.eabmodel.juegazosgabazo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eabmodel.juegazosgabazo.objects.User
import com.google.gson.Gson

class CartPage: AppCompatActivity() {

    val gson = Gson()
    lateinit var bag: ImageView
    lateinit var interactions: ImageView
    lateinit var profile: ImageView
    lateinit var textPrueba: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.interactions_page)
        Log.d("LIFECYCLE", "onCreate Shopwindow")
        init()
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)

        if (TemporaryStorage.cart.isEmpty()){
            textPrueba.text = "Your cart is empty!"
        } else {
            textPrueba.text = TemporaryStorage.cart.toString()

        }


        bag.setOnClickListener {
            val intent = Intent(this, Shopwindow::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        }

        profile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user", userJson)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }


    }

    fun init() {
        bag = findViewById(R.id.bag)
        interactions = findViewById(R.id.interactions)
        profile = findViewById(R.id.profile)
        textPrueba = findViewById(R.id.textView2)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart MainPage")
    }


}