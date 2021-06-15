package com.eabmodel.juegazosgabazo

import com.google.gson.annotations.Expose

data class User(@Expose val username: String,
                @Expose val password: String,
                @Expose val name: String,
                @Expose val profilePic: String = "@drawable/n2",
                @Expose val funds: Double = 0.01)