package com.eabmodel.juegazosgabazo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eabmodel.juegazosgabazo.R
import com.eabmodel.juegazosgabazo.adapters.CartAdapter
import com.eabmodel.juegazosgabazo.adapters.MyOrdersAdapter
import com.eabmodel.juegazosgabazo.adapters.ShopwindowAdapter
import com.eabmodel.juegazosgabazo.controllers.DBController
import com.eabmodel.juegazosgabazo.controllers.SPController
import com.eabmodel.juegazosgabazo.fromJson
import com.eabmodel.juegazosgabazo.objects.Order
import com.eabmodel.juegazosgabazo.objects.User
import com.google.gson.Gson

class MyOrdersPage : AppCompatActivity() {

    val gson = Gson()
    lateinit var emptyOrdersText: TextView
    lateinit var dbController: DBController
    lateinit var spController: SPController
    lateinit var adapter: MyOrdersAdapter
    lateinit var listOfOrders: List<Order>
    lateinit var recyclerViewOrders: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_orders_page)
        init()
        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        var userActive: User = dbController.verifyUser(user.username, user.password)!!
        user.funds = userActive.funds

        recyclerViewOrders = findViewById(R.id.recyclerViewOrders)
        listOfOrders = dbController.orderOrdersByDate(user)

        adapter = MyOrdersAdapter(this, listOfOrders)
        recyclerViewOrders.adapter = adapter
        recyclerViewOrders.layoutManager = LinearLayoutManager(this)

        if (!listOfOrders.isEmpty()){
            emptyOrdersText.text = ""
        }


    }

    fun init(){
        emptyOrdersText = findViewById(R.id.emptyOrdersText)


        dbController = DBController(this)
        spController = SPController()
    }
}