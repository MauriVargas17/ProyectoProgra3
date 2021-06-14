package com.eabmodel.juegazosgabazo

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class MainPage: AppCompatActivity() {

    val gson = Gson()
    lateinit var bag: ImageView
    lateinit var interactions: ImageView
    lateinit var profile: ImageView
    lateinit var plus: ImageView
    lateinit var cart: ImageView
    lateinit var searchBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        Log.d("LIFECYCLE", "onCreate MainPage")
    init()
        val bienvenidaJson = intent.getStringExtra("user")
        val bienvenida: User = gson.fromJson(bienvenidaJson!!)
        Toast.makeText(this, "Bienvenido ${bienvenida.username}", Toast.LENGTH_SHORT).show()

        bag.setOnClickListener{
            searchBar.hint = "Hahahahaha"

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