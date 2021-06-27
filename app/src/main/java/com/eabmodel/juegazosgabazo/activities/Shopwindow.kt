package com.eabmodel.juegazosgabazo.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eabmodel.juegazosgabazo.*
import com.eabmodel.juegazosgabazo.adapters.ShopwindowAdapter
import com.eabmodel.juegazosgabazo.controllers.DBController
import com.eabmodel.juegazosgabazo.controllers.SPController
import com.eabmodel.juegazosgabazo.objects.Product
import com.eabmodel.juegazosgabazo.objects.User
import com.google.gson.Gson

class Shopwindow: AppCompatActivity(), AdapterView.OnItemSelectedListener{

    val gson = Gson()
    lateinit var bag: ImageView
    lateinit var interactions: ImageView
    lateinit var profile: ImageView
    lateinit var explore: TextView
    lateinit var recyclerViewShopwindow: RecyclerView
    lateinit var nintendoButton: ImageView
    lateinit var xboxButton: ImageView
    lateinit var steamButton: ImageView
    lateinit var psButton: ImageView
    lateinit var dropdown: Spinner
    lateinit var dbController: DBController
    lateinit var spController: SPController
    lateinit var search: ImageView
    lateinit var logo: ImageView
    lateinit var listOfProducts: List<Product>
    lateinit var adapter: ShopwindowAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopwindow_page)
        Log.d("LIFECYCLE", "onCreate Shopwindow")
        init()


    //android.R.layout.simple_spinner_item GOES AS PARAMETER IN THE FUNCTION BELOW
        ArrayAdapter.createFromResource(this,
            R.array.filtering,
            R.layout.text_view
        ).also { adapterDD ->
            adapterDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropdown.adapter = adapterDD
        }

        dropdown.setOnItemSelectedListener(this)


        //val filters = listOf<String>("Title", "Price", "Type")
       // val adapterDropDown = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
       // dropdown.adapter = adapterDropDown
        /**
         * Products Creation
         */

       // dbController.deleteAllProducts()




        recyclerViewShopwindow = findViewById(R.id.recyclerViewShopwindow)
        listOfProducts = dbController.orderProductsByTitle()

        adapter = ShopwindowAdapter(this, listOfProducts )
        recyclerViewShopwindow.adapter = adapter
        recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)

        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)


/*
         dbController.createUser("p", "p", "p")
        val user = dbController.verifyUser("p", "p")!!

 */



        var userActive: User = dbController.verifyUser(user.username, user.password)!!
        user.funds = userActive.funds
        //Toast.makeText(this, " ${user.username}, what are you buying today?", Toast.LENGTH_SHORT).show()



        /**
         * Button to add product to cart FOR PRODUCTPAGE ACTIVITY
         */
        /*
        adapter.setOnProductClickListener {
            TemporaryStorage.cart.add(it)
            Toast.makeText(this, " ${it.title} was addded to your Cart", Toast.LENGTH_SHORT).show()
        }

         */


        adapter.setOnProductClickListener {
            val intent = Intent(this, ProductPage::class.java)
            val productJson = gson.toJson(it)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            intent.putExtra("product",productJson)
            startActivity(intent)


        }



        profile.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        interactions.setOnClickListener{
            val intent = Intent(this, CartPage::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        nintendoButton.setOnClickListener{

          listOfProducts = dbController.orderProductsByPlatform("Nintendo")

            adapter.list = listOfProducts
            adapter.notifyDataSetChanged()
            recyclerViewShopwindow.adapter = adapter
            recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)
            /**
             * LOGCAT TO CHECK CORRECT DATA FROM DB
             */
            /*
            listOfProducts.forEach {
                Log.d("Shopwindow", "${it.title}")
            }

             */


           // Toast.makeText(this, " nintendo button", Toast.LENGTH_SHORT).show()
        }
        xboxButton.setOnClickListener{
            listOfProducts = dbController.orderProductsByPlatform("Xbox Live")
            adapter.list = listOfProducts
            recyclerViewShopwindow.adapter = adapter
            recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)
            //Toast.makeText(this, "xbox button", Toast.LENGTH_SHORT).show()
        }
        steamButton.setOnClickListener{
            listOfProducts = dbController.orderProductsByPlatform("Steam")
            adapter.list = listOfProducts
            recyclerViewShopwindow.adapter = adapter
            recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)

        }
        psButton.setOnClickListener{
            listOfProducts = dbController.orderProductsByPlatform("Play Station")
            adapter.list = listOfProducts
            recyclerViewShopwindow.adapter = adapter
            recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)

        }

        search.setOnClickListener {

            listOfProducts = dbController.searchProduct(explore.text.toString())
            adapter.list = listOfProducts
            recyclerViewShopwindow.adapter = adapter
            recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)


        }

        logo.setOnClickListener {
            Toast.makeText(this, "For our dear friend Gabazo", Toast.LENGTH_SHORT).show()
        }





    }

    fun init(){
        bag = findViewById(R.id.bag)
        interactions = findViewById(R.id.interactions)
        profile = findViewById(R.id.profile)
        dbController = DBController(this)
        explore = findViewById(R.id.explore)
        nintendoButton = findViewById(R.id.nintendo)
        xboxButton = findViewById(R.id.xbox)
        steamButton = findViewById(R.id.steam)
        psButton = findViewById(R.id.ps)
        dropdown = findViewById(R.id.spinner)
        search = findViewById(R.id.searchButton)
        logo = findViewById(R.id.logoJG)
        spController = SPController()
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "onStart MainPage")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var filter: String = parent!!.getItemAtPosition(position).toString()
        when(filter){
            "Price" -> listOfProducts = dbController.orderProductsByPrice()
            "Title" -> listOfProducts = dbController.orderProductsByTitle()
            "Type"  -> listOfProducts = dbController.orderProductsByType()
        }

        adapter.list = listOfProducts
        recyclerViewShopwindow.adapter = adapter
        recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)
        //Toast.makeText(this, "${filter} selected", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}