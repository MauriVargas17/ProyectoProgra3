package com.eabmodel.juegazosgabazo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.eabmodel.juegazosgabazo.objects.Product
import com.google.gson.Gson


class ProductPage : AppCompatActivity() {
    val gson = Gson()
    lateinit var addButton: View
    lateinit var titleText: TextView
    lateinit var typeProduct: TextView
    lateinit var platformProduct: TextView
    lateinit var priceProduct: TextView
    lateinit var descriptionProduct: TextView
    lateinit var imageProduct: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_page)
        init()
        val userJson = intent.getStringExtra("user")

        val productJson = intent.getStringExtra("product")
        val product: Product = gson.fromJson(productJson!!)
        titleText.text = " ${product.title}"
        typeProduct.text = " ${product.type}"
        platformProduct.text =  " ${product.platform}"
        priceProduct.text =  " ${product.price}"
        descriptionProduct.text =  " ${product.description}"
        imageProduct.setImageResource(product.image)


        addButton.setOnClickListener{
            TemporaryStorage.cart.add(product)
            Toast.makeText(this, " ${product.title} was addded to your Cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Shopwindow::class.java)

            intent.putExtra("user", userJson)
            startActivity(intent)
        }


    }

    fun init(){
        addButton = findViewById(R.id.continueButton)
        titleText = findViewById(R.id.titleText)
        typeProduct = findViewById(R.id.typeProduct)
        platformProduct = findViewById(R.id.platformProduct)
        priceProduct = findViewById(R.id.priceProduct)
        descriptionProduct = findViewById(R.id.descriptionProduct)
        imageProduct = findViewById(R.id.imageProduct)
    }
}