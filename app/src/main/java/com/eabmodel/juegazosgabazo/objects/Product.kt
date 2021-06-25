package com.eabmodel.juegazosgabazo.objects

import android.widget.ImageView
import com.google.gson.annotations.Expose

data class Product(@Expose val title: String,
                   @Expose val seller: String,
                   @Expose val platform: String,
                   @Expose val type: String,
                   @Expose val description: String,
                   @Expose val price: Double,
                   @Expose val image: Int)