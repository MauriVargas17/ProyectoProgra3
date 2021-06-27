package com.eabmodel.juegazosgabazo.activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.eabmodel.juegazosgabazo.R
import com.eabmodel.juegazosgabazo.controllers.DBController
import com.eabmodel.juegazosgabazo.controllers.SPController
import com.eabmodel.juegazosgabazo.fromJson
import com.eabmodel.juegazosgabazo.objects.User
import com.google.gson.Gson

class AddFundsPage : AppCompatActivity() {
    val dbController = DBController(this)
    val gson = Gson()
    lateinit var nameLastname: TextView
    lateinit var username: TextView
    lateinit var totalBalance: TextView
    lateinit var addFundsButton: View
    lateinit var visaButton: ImageView
    lateinit var paypalButton: ImageView
    lateinit var bnbButton: ImageView
    lateinit var amountToBeAdded: TextView
    lateinit var checkPassword: TextView
    lateinit var spController: SPController
    var methodSelected: Boolean = false

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_funds_page)
        init()
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        var userActive: User = dbController.verifyUser(user.username, user.password)!!
        user.funds = userActive.funds
        nameLastname.text = " ${user.name}"
        username.text = "  ${user.username}"
        totalBalance.text = "   ${user.funds.toString()}"



        visaButton.setOnClickListener {
            methodSelected = true
            visaButton.setImageResource(R.drawable.visa_black)
            paypalButton.setImageResource(R.drawable.paypal)
            bnbButton.setImageResource(R.drawable.bnb)

        }
        paypalButton.setOnClickListener {
            methodSelected = true
            visaButton.setImageResource(R.drawable.visa)
            paypalButton.setImageResource(R.drawable.paypal_black)
            bnbButton.setImageResource(R.drawable.bnb)

        }
        bnbButton.setOnClickListener {
            methodSelected = true
            visaButton.setImageResource(R.drawable.visa)
            paypalButton.setImageResource(R.drawable.paypal)
            bnbButton.setImageResource(R.drawable.bnb_black)

        }

        addFundsButton.setOnClickListener{
            if (amountToBeAdded.text.toString() == ""){
                Toast.makeText(this, "No amount introduced", Toast.LENGTH_SHORT).show()
            }else if (amountToBeAdded.text.toString().toDouble() >= 100000.0){
                Toast.makeText(this, "Inadequate amount", Toast.LENGTH_SHORT).show()
            }  else if (checkPassword.text.toString() == ""){
                Toast.makeText(this, "Password Required", Toast.LENGTH_SHORT).show()
            } else if (checkPassword.text.toString() != user.password){
                    Toast.makeText(this, "Password is Incorrect", Toast.LENGTH_SHORT).show()
            } else if (!methodSelected){
                Toast.makeText(this, "No payment method was selected", Toast.LENGTH_SHORT).show()
            } else {
                dbController.addFunds(user, amountToBeAdded.text.toString().toDouble())
                totalBalance.text = "   ${user.funds.toString()}"
                Toast.makeText(this, "Funds added successfully!", Toast.LENGTH_SHORT).show()

            }


        }



    }

    fun init(){
        spController = SPController()
        nameLastname = findViewById(R.id.name)
        username = findViewById(R.id.username)
        totalBalance = findViewById(R.id.funds)
        addFundsButton = findViewById(R.id.addFundsView)
        visaButton = findViewById(R.id.visaButton)
        paypalButton = findViewById(R.id.paypalButton)
        bnbButton = findViewById(R.id.bnbButton)
        amountToBeAdded = findViewById(R.id.amountToBeAdded)
        checkPassword = findViewById(R.id.checkPassword)
    }
}