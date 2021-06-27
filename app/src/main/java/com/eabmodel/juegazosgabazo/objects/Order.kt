package com.eabmodel.juegazosgabazo.objects

import com.google.gson.annotations.Expose
import java.sql.Date


data class Order(@Expose val username: String, @Expose val date: String,
                 @Expose val title: String,
                 @Expose val seller: String,
                 @Expose val platform: String,
                 @Expose val type: String,
                 @Expose val description: String,
                 @Expose val price: Double,
                 @Expose val image: Int)