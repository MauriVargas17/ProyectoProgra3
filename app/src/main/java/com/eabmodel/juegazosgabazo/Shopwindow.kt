package com.eabmodel.juegazosgabazo

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eabmodel.juegazosgabazo.controllers.DBController
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
        ArrayAdapter.createFromResource(this, R.array.filtering, R.layout.text_view).also { adapterDD ->
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

        /*
        dbController.createProduct("Sea of Thieves", "Sacrifice_shop", "Xbox Live", "Key", "Weigh anchor, hoist the sails, walk the plank, play the hurdy-gurdy? Sea of Thieves is a swashbuckling adventure on the high seas for you, your friends, and a bunch of random people whose ships you can try to plunder.Developed by a seasoned developer Rare, SoT is great fun for anyone with love for pirate's life and badly sung shanties.", 18.35, R.drawable.por2)
        dbController.createProduct("Biomutant", "Games_codes", "Play Station", "Key", "The Tree of Life is dying, and the pollution is spreading all over the world, corrupting and twisting life coming in contact with it. In a landscape ruled by six factions, it falls to a single determined warrior to be the saviour and uniter or the agent of destruction and conquest. Biomutant is a third-person open-world action-RPG set in post-apocalypse world inhabited by sentient anthropomorphic animals.", 42.45, R.drawable.por1)
        dbController.createProduct("Minecraft", "Velonic", "Xbox Live", "Key", "The world is your playground. Minecraft has the creativity factor and the flexibility matched only by playing with LEGO. There is no better game on PC or console to capture the joy of holding the forces of creation in your hand. Whether you choose to explore the boundless world or to create a stronghold and rule over the region, Minecraft supplies you with abilities to do so.", 15.99, R.drawable.por3)
        dbController.createProduct("Resident Evil VILLAGE", "Forestgarden", "Xbox Live", "Key", "Resident Evil: Village is the 8th installment of popular survival horror video game franchise created and released by Capcom. Developers once again decided to realize their newest game using the First Person perspective, which maximizes the immersion and makes the whole experience much more exciting and terrifying. Similarly like it was in the previous Resident Evil game, Resident Evil 8 features very high-quality visuals, thanks to photorealistic graphics. ", 33.91, R.drawable.por4)
        dbController.createProduct("Mario Golf Super Rush", "Nintenshop", "Nintendo", "Key", "Hit the green with up to four players locally* or online** and golf with friends from the Super Mario™ series like Mario, Peach, Yoshi, and more! Modes range from Standard Golf to the energetic Speed Golf and an exciting Golf Adventure. Simple motion or button controls make it easy for both new players and seasoned pros to drive and putt.", 59.99, R.drawable.por5)
        dbController.createProduct("FIFA 21 Deluxe Edition", "Games_codes", "Play Station", "Key", "FIFA 21 is a football (soccer) sports game developed by EA Vancouver and published by EA Sports. The game is yet another installment of classic football simulation series, dating back to the late nineties of the last century. In FIFA21, the player will once again play as their favorite team, participating in matches with other players through online multiplayer or against the computer offline. The video game offers improvements to its various modes, including FIFA Ultimate Team, Volta, and Career modes. Gameplay mechanics have also been revamped, offering even more intuitive controls. FIFA 21 was received positively by the critics, who praised improved gameplay mechanics, additional players I FUT mode, and demanding Career mode.", 21.50, R.drawable.por6)

         */


        recyclerViewShopwindow = findViewById(R.id.recyclerViewShopwindow)
        listOfProducts = dbController.orderProductsByTitle()

        adapter = ShopwindowAdapter(this, listOfProducts )
        recyclerViewShopwindow.adapter = adapter
        recyclerViewShopwindow.layoutManager = LinearLayoutManager(this)

        val userJson = intent.getStringExtra("user")
        val user: User = gson.fromJson(userJson!!)
        //Toast.makeText(this, " ${user.username}, what are you buying today?", Toast.LENGTH_SHORT).show()

        adapter.setOnProductClickListener {
            TemporaryStorage.cart.add(it)
            Toast.makeText(this, " ${it.title} was addded to your Cart", Toast.LENGTH_SHORT).show()
        }




        profile.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            val userJson = gson.toJson(user)
            intent.putExtra("user",userJson)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        interactions.setOnClickListener{
            val intent = Intent(this, Interactions::class.java)
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