package com.eabmodel.juegazosgabazo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class Profile: AppCompatActivity() {

    val gson = Gson()
    lateinit var bag: ImageView
    lateinit var interactions: ImageView
    lateinit var profile: ImageView
    /*
    lateinit var plus: ImageView
    lateinit var cart: ImageView
    lateinit var searchBar: EditText

     */
    lateinit var nameLastname: TextView
    lateinit var username: TextView
    lateinit var totalBalance: TextView
    lateinit var logoutButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)
        Log.d("LIFECYCLE", "onCreate Profile")
        init()
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        nameLastname.text = " ${user.name}"
        username.text = "  ${user.username}"
        totalBalance.text = "   ${user.funds.toString()}"

        bag.setOnClickListener{
            val intent = Intent(this, Shopwindow::class.java)
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

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }



    }

    fun init(){
        bag = findViewById(R.id.bag)
        interactions = findViewById(R.id.interactions)
        profile = findViewById(R.id.profile)
        /*
        plus = findViewById(R.id.plus)
        cart = findViewById(R.id.cart)
        searchBar = findViewById(R.id.search)

         */
        nameLastname = findViewById(R.id.name)
        username = findViewById(R.id.username)
        totalBalance = findViewById(R.id.funds)
        logoutButton = findViewById(R.id.logoutButton)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart MainPage")
    }


}