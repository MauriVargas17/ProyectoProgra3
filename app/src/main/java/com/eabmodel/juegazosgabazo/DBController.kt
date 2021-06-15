package com.eabmodel.juegazosgabazo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DBController(context: Context): SQLiteOpenHelper(context, "Users", null, 2)  {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Users (${BaseColumns._ID} INTEGER PRIMARY KEY, Username TEXT, Password TEXT, Name TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("ALTER TABLE Users ADD COLUMN Funds Double")
    }

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