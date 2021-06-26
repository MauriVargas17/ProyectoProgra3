package com.eabmodel.juegazosgabazo.controllers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.eabmodel.juegazosgabazo.R
import com.eabmodel.juegazosgabazo.objects.Product
import com.eabmodel.juegazosgabazo.objects.User

class DBController(context: Context): SQLiteOpenHelper(context, "Users", null, 3)  {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Users (${BaseColumns._ID} INTEGER PRIMARY KEY, Username TEXT, Password TEXT, Name TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       // db?.execSQL("ALTER TABLE Users ADD COLUMN Funds Double")
        db?.execSQL("CREATE TABLE Products (${BaseColumns._ID} INTEGER PRIMARY KEY, Title TEXT, Seller TEXT, Platform TEXT, Type TEXT, Description LONGTEXT, Price Double, Image Int)")

    }

    /**
     * Products DB
     */

    fun createProduct(title: String, seller: String, platform: String, type: String, description: String, price: Double, image: Int): Boolean{
        val columns = ContentValues()
        columns.put("Title", title)
        columns.put("Seller", seller)
        columns.put("Platform", platform)
        columns.put("Type", type)
        columns.put("Description", description)
        columns.put("Price", price)
        columns.put("Image", image)
        writableDatabase.insert("Products", null, columns)
        return true
    }

    fun searchProduct(search: String): List<Product> {

        val cursor = readableDatabase.rawQuery("SELECT * FROM Products WHERE Title LIKE \"$search%\"", arrayOf())
        val listOfProducts = mutableListOf<Product>()
        while(cursor.moveToNext()) {
            val product= Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7))
            listOfProducts.add(product)
        }

        return listOfProducts

    }

    fun orderProductsByPlatform(platform: String): List<Product> {

        val cursor = readableDatabase.rawQuery("SELECT * FROM Products WHERE Platform = \"$platform\"", arrayOf())
        val listOfProducts = mutableListOf<Product>()
        while(cursor.moveToNext()) {
            val product= Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7))
            listOfProducts.add(product)
        }
        Log.d("DBProducts", "Returning list filtered according to platform")
        return listOfProducts

    }

    fun orderProductsByPrice(): List<Product> {

        val cursor = readableDatabase.rawQuery("SELECT * FROM Products ORDER BY Price ASC", arrayOf())
        val listOfProducts = mutableListOf<Product>()
        while(cursor.moveToNext()) {
            val product= Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7))
            listOfProducts.add(product)
        }

        return listOfProducts

    }

    fun orderProductsByTitle(): List<Product> {

        val cursor = readableDatabase.rawQuery("SELECT * FROM Products ORDER BY Title ASC", arrayOf())
        val listOfProducts = mutableListOf<Product>()
        while(cursor.moveToNext()) {
            val product= Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7))
            listOfProducts.add(product)
        }

        return listOfProducts

    }

    fun orderProductsByType(): List<Product> {

        val cursor = readableDatabase.rawQuery("SELECT * FROM Products ORDER BY Type ASC", arrayOf())
        val listOfProducts = mutableListOf<Product>()
        while(cursor.moveToNext()) {
            val product= Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7))
            listOfProducts.add(product)
        }

        return listOfProducts

    }

    fun deleteProduct(title: String) {
        writableDatabase.delete("Products", "Title = \"$title\"", arrayOf())
    }

    fun deleteAllProducts(): Boolean {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Products", arrayOf())
        val listOfProducts = mutableListOf<Product>()
        while(cursor.moveToNext()) {
            val product= Product(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6), cursor.getInt(7))
            deleteProduct(product.title)
        }
        return true
    }
    /**
     * Users DB
     */

    fun createUser(username: String, password: String, name: String): Boolean {
        if (!verifyUserExistance(username, password, 0)) {
            val columns = ContentValues()
            columns.put("Username", username)
            columns.put("Password", password)
            columns.put("Name", name)
            columns.put("Funds", 0.0)
            writableDatabase.insert("Users", null, columns)
            return true
        }
       return false
    }

    fun verifyUser(username: String, password: String): User? {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Users WHERE Username = \"$username\" AND Password = \"$password\"", arrayOf())
        if(cursor.count == 0) {
            return null
        }
        cursor.moveToFirst()
        val user = User(cursor.getString(1), cursor.getString(2), cursor.getString(3))
        cursor.close()
        return user
    }

    fun verifyUserExistance(username: String, password: String, nil: Any): Boolean {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Users WHERE Username = \"$username\"", arrayOf())
        if(cursor.count == 0) {
            return false
        }
        cursor.moveToFirst()
        val user = User(cursor.getString(1), cursor.getString(2), cursor.getString(3))
        cursor.close()
        return true
    }

    fun verifyUser(username: String, password: String, nil: Any): Boolean {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Users WHERE Username = \"$username\" AND Password = \"$password\"", arrayOf())
        if(cursor.count == 0) {
            return false
        }
        cursor.moveToFirst()
        val user = User(cursor.getString(1), cursor.getString(2), cursor.getString(3))
        cursor.close()
        return true
    }

    fun obtainAllUsers(): List<User> {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Users", arrayOf())
        val listOfUsers = mutableListOf<User>()
        while(cursor.moveToNext()) {
            val user= User(cursor.getString(1), cursor.getString(2), cursor.getString(3))
            listOfUsers.add(user)
        }
        return listOfUsers
    }

    fun deleteAllUsers(): Boolean {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Users", arrayOf())
        val listOfUsers = mutableListOf<User>()
        while(cursor.moveToNext()) {
            val user= User(cursor.getString(1), cursor.getString(2), cursor.getString(3))
            deleteUser(user.username)
        }
        return true
    }

    fun deleteUser(username: String) {
        writableDatabase.delete("Users", "Username = \"$username\"", arrayOf())
    }

    fun changePassword(username: String, password: String, newPassword: String) {
        val columns = ContentValues()
        columns.put("Password", newPassword)
        writableDatabase.update("Users", columns, "Username = \"$username\" AND Password = \"$password\"", arrayOf())
    }
}