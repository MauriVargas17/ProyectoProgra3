package com.eabmodel.juegazosgabazo.controllers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.eabmodel.juegazosgabazo.objects.User
import com.google.gson.Gson

class SPController {

    val gson = Gson()

    companion object{
        private const val KEY_USER = "user"
    }

    fun saveUser(context: Context, user: User?){
        val sharedPreferences = context.getSharedPreferences("SPUsuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USER, gson.toJson(user))
        editor.commit()
    }

    fun getUser(context: Context): User?{
        val sharedPreferences = context.getSharedPreferences("SPUsuario", MODE_PRIVATE)
        if (sharedPreferences.contains(KEY_USER)){
            val userString = sharedPreferences.getString(KEY_USER, null)
            val user = gson.fromJson(userString, User::class.java)
            return user
        }else {
            return null
        }

    }

    fun deleteUser(context: Context){
        val sharedPreferences = context.getSharedPreferences("SPUsuario", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        Log.d("SharedPreferences", "SPController deleted user")
        editor.remove(KEY_USER)
        editor.commit()
    }


}