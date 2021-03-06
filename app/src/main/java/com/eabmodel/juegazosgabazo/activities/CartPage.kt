package com.eabmodel.juegazosgabazo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eabmodel.juegazosgabazo.R
import com.eabmodel.juegazosgabazo.adapters.CartAdapter
import com.eabmodel.juegazosgabazo.controllers.DBController
import com.eabmodel.juegazosgabazo.fromJson
import com.eabmodel.juegazosgabazo.objects.Product
import com.eabmodel.juegazosgabazo.objects.User
import com.eabmodel.juegazosgabazo.sigletons.TemporaryStorage
import com.google.gson.Gson
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CartPage: AppCompatActivity() {

    val gson = Gson()
    lateinit var bag: ImageView
    lateinit var interactions: ImageView
    lateinit var profile: ImageView
    lateinit var emptyCartText: TextView
    lateinit var dbController: DBController
    lateinit var adapter: CartAdapter
    lateinit var recyclerViewCart: RecyclerView
    lateinit var listOfProducts: MutableList<Product>
    lateinit var totalAmount: TextView
    lateinit var checkout: View
    lateinit var continueButton: View
    var sum: Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_page)
        Log.d("LIFECYCLE", "onCreate Shopwindow")
        init()
        totalAmount.text = "-"
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        var userActive: User = dbController.verifyUser(user.username, user.password)!!
        user.funds = userActive.funds



        if (listOfProducts.isEmpty()){
            emptyCartText.text = "Your cart is empty!"
            checkout.visibility = View.INVISIBLE

        } else {
           // emptyCartText.text = TemporaryStorage.cart.toString()
            emptyCartText.text = ""

            listOfProducts.forEach {
                sum += it.price
            }
            totalAmount.text = sum.toString()
            checkout.visibility = View.VISIBLE

        }

        recyclerViewCart = findViewById(R.id.recyclerViewCart)
        listOfProducts = TemporaryStorage.cart

        adapter = CartAdapter(this, listOfProducts )
        recyclerViewCart.adapter = adapter
        recyclerViewCart.layoutManager = LinearLayoutManager(this)

        adapter.setOnProductClickListener {
            TemporaryStorage.cart.remove(it)
            adapter.list = listOfProducts
            adapter.notifyDataSetChanged()
            sum -= it.price
            sum = BigDecimal(sum).setScale(2, RoundingMode.HALF_EVEN).toDouble()
            totalAmount.text = sum.toString()

            Toast.makeText(this, " ${it.title} was deleted from your Cart", Toast.LENGTH_SHORT).show()
        }

        continueButton.setOnClickListener{
            if (user.funds >= sum && sum > 0.00){
                Log.d("CART_PAGE", "Continue button pressed")
                user.funds -= sum
                dbController.deductFunds(user, sum)
                var currentDate = LocalDateTime.now()
                val dateString = currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
                listOfProducts.forEach {
                    dbController.createOrder(user.username, dateString, it.title, it.seller, it.platform, it.type, it.description, it.price, it.image)
                }
                val iteratorTemp = TemporaryStorage.cart.iterator()
                val iteratorList = listOfProducts.iterator()

                TemporaryStorage.cart.removeAll(listOfProducts)
                /*
                while(iteratorTemp.hasNext()) {
                   // val item = iterator.next()
                    iteratorTemp.remove()
                }

                 */
                sum = 0.0
                Log.d("CART_PAGE", "empty cart")
                checkout.visibility = View.INVISIBLE
                Toast.makeText(this, "Successful purchase!", Toast.LENGTH_SHORT).show()


            } else if(user.funds < sum) {
                Toast.makeText(this, "Not enough funds", Toast.LENGTH_SHORT).show()
            }
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
        emptyCartText = findViewById(R.id.emptyCartText)
        totalAmount = findViewById(R.id.total)
        listOfProducts = TemporaryStorage.cart
        checkout = findViewById<ConstraintLayout>(R.id.checkout)
        continueButton = findViewById(R.id.continueButton)
        dbController = DBController(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart MainPage")
    }


}