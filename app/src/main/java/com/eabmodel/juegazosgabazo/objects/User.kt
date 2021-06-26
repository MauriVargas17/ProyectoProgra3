package com.eabmodel.juegazosgabazo.objects

import com.google.gson.annotations.Expose

data class User(@Expose val username: String,
                @Expose val password: String,
                @Expose val name: String,
                @Expose val profilePic: String = "@drawable/n2",
                @Expose var funds: Double = 0.00)