package com.eabmodel.juegazosgabazo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.eabmodel.juegazosgabazo.*
import com.eabmodel.juegazosgabazo.controllers.DBController
import com.eabmodel.juegazosgabazo.controllers.SPController
import com.eabmodel.juegazosgabazo.objects.User
import com.google.gson.Gson

class Profile: AppCompatActivity() {

    val gson = Gson()
    lateinit var bag: ImageView
    lateinit var interactions: ImageView
    lateinit var profile: ImageView
    lateinit var addFunds: View
    lateinit var nameLastname: TextView
    lateinit var username: TextView
    lateinit var totalBalance: TextView
    lateinit var logoutButton: View
    lateinit var spController: SPController
    lateinit var dbController: DBController
    lateinit var myOrders: View
    lateinit var settings: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)
        Log.d("LIFECYCLE", "onCreate Profile")
        init()
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        var userActive: User = dbController.verifyUser(user.username, user.password)!!
        user.funds = userActive.funds
        nameLastname.text = " ${user.name}"
        username.text = "  ${user.username}"
        totalBalance.text = "   ${user.funds.toString()}"





        bag.setOnClickListener{
            val intent = Intent(this, Shopwindow::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        interactions.setOnClickListener{
            val intent = Intent(this, CartPage::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginPage::class.java)
            spController.deleteUser(this)
            startActivity(intent)

        }

        addFunds.setOnClickListener {
            val intent = Intent(this, AddFundsPage::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
        }
/*
        settings.setOnClickListener{
            val intent = Intent(this, Settings::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)

        }

 */

        myOrders.setOnClickListener {
            val intent = Intent(this, MyOrdersPage::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
        }


    }

    fun init(){
        spController = SPController()
        dbController = DBController(this)
        bag = findViewById(R.id.bag)
        interactions = findViewById(R.id.interactions)
        profile = findViewById(R.id.profile)
        nameLastname = findViewById(R.id.name)
        username = findViewById(R.id.username)
        totalBalance = findViewById(R.id.funds)
        logoutButton = findViewById(R.id.logoutButton)
        addFunds = findViewById(R.id.addFundsView)
        settings = findViewById(R.id.continueButton)
        myOrders = findViewById(R.id.myOrdersView)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart MainPage")
    }


}