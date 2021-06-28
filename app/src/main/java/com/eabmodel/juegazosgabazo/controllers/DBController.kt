package com.eabmodel.juegazosgabazo.controllers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import com.eabmodel.juegazosgabazo.R
import com.eabmodel.juegazosgabazo.objects.Order
import com.eabmodel.juegazosgabazo.objects.Product
import com.eabmodel.juegazosgabazo.objects.User
import java.math.BigDecimal
import java.math.RoundingMode
import java.sql.Date
import java.sql.Timestamp

class DBController(context: Context): SQLiteOpenHelper(context, "Users", null, 7)  {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Users (${BaseColumns._ID} INTEGER PRIMARY KEY, Username TEXT, Password TEXT, Name TEXT, Funds Double)")
        db?.execSQL("CREATE TABLE Products (${BaseColumns._ID} INTEGER PRIMARY KEY, Title TEXT, Seller TEXT, Platform TEXT, Type TEXT, Description LONGTEXT, Price Double, Image Int)")
        db?.execSQL("CREATE TABLE Orders (${BaseColumns._ID} INTEGER PRIMARY KEY, Username TEXT, Date TEXT,Title TEXT, Seller TEXT, Platform TEXT, Type TEXT, Description LONGTEXT, Price Double, Image Int)")
        /**
         * Creating Products
         */
        setInitialProducts(db)

        Log.d("DBController", "onCreate DB")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       // db?.execSQL("ALTER TABLE Users ADD COLUMN Funds Double")
        db?.execSQL("DROP TABLE IF EXISTS " + "Users")
        db?.execSQL("DROP TABLE IF EXISTS " + "Products")
        db?.execSQL("DROP TABLE IF EXISTS " + "Orders")
        onCreate(db)
        Log.d("DBController", "onUpdate DB")
    }

    fun setInitialProducts(db: SQLiteDatabase?){
        createProduct(db,"Sea of Thieves", "Sacrifice_shop", "Xbox Live", "Key", "Weigh anchor, hoist the sails, walk the plank, play the hurdy-gurdy? Sea of Thieves is a swashbuckling adventure on the high seas for you, your friends, and a bunch of random people whose ships you can try to plunder.Developed by a seasoned developer Rare, SoT is great fun for anyone with love for pirate's life and badly sung shanties.", 18.35, R.drawable.por2)
        createProduct(db,"Biomutant", "Games_codes", "Play Station", "Key", "The Tree of Life is dying, and the pollution is spreading all over the world, corrupting and twisting life coming in contact with it. In a landscape ruled by six factions, it falls to a single determined warrior to be the saviour and uniter or the agent of destruction and conquest. Biomutant is a third-person open-world action-RPG set in post-apocalypse world inhabited by sentient anthropomorphic animals.", 42.45, R.drawable.por1)
        createProduct(db,"Minecraft", "Velonic", "Xbox Live", "Key", "The world is your playground. Minecraft has the creativity factor and the flexibility matched only by playing with LEGO. There is no better game on PC or console to capture the joy of holding the forces of creation in your hand. Whether you choose to explore the boundless world or to create a stronghold and rule over the region, Minecraft supplies you with abilities to do so.", 15.99, R.drawable.por3)
        createProduct(db,"Resident Evil VILLAGE", "Forestgarden", "Xbox Live", "Key", "Resident Evil: Village is the 8th installment of popular survival horror video game franchise created and released by Capcom. Developers once again decided to realize their newest game using the First Person perspective, which maximizes the immersion and makes the whole experience much more exciting and terrifying. Similarly like it was in the previous Resident Evil game, Resident Evil 8 features very high-quality visuals, thanks to photorealistic graphics. ", 33.91, R.drawable.por4)
        createProduct(db,"Mario Golf Super Rush", "Nintenshop", "Nintendo", "Key", "Hit the green with up to four players locally* or online** and golf with friends from the Super Mario™ series like Mario, Peach, Yoshi, and more! Modes range from Standard Golf to the energetic Speed Golf and an exciting Golf Adventure. Simple motion or button controls make it easy for both new players and seasoned pros to drive and putt.", 59.99, R.drawable.por5)
        createProduct(db,"FIFA 21 Deluxe Edition", "Games_codes", "Play Station", "Key", "FIFA 21 is a football (soccer) sports game developed by EA Vancouver and published by EA Sports. The game is yet another installment of classic football simulation series, dating back to the late nineties of the last century. In FIFA21, the player will once again play as their favorite team, participating in matches with other players through online multiplayer or against the computer offline. The video game offers improvements to its various modes, including FIFA Ultimate Team, Volta, and Career modes. Gameplay mechanics have also been revamped, offering even more intuitive controls. FIFA 21 was received positively by the critics, who praised improved gameplay mechanics, additional players I FUT mode, and demanding Career mode.", 21.51, R.drawable.por6)


    }

    /**
     * Products DB
     */

    fun createProduct(db: SQLiteDatabase?, title: String, seller: String, platform: String, type: String, description: String, price: Double, image: Int): Boolean{
        val columns = ContentValues()
        columns.put("Title", title)
        columns.put("Seller", seller)
        columns.put("Platform", platform)
        columns.put("Type", type)
        columns.put("Description", description)
        columns.put("Price", price)
        columns.put("Image", image)
        db?.insert("Products", null, columns)
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
        val user = User(cursor.getString(1), cursor.getString(2), cursor.getString(3), "", cursor.getDouble(4))
        cursor.close()
        return user
    }

    fun verifyUserExistance(username: String, password: String, nil: Any): Boolean {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Users WHERE Username = \"$username\"", arrayOf())
        if(cursor.count == 0) {
            return false
        }
        cursor.moveToFirst()

        cursor.close()
        return true
    }

    fun verifyUser(username: String, password: String, nil: Any): Boolean {
        val cursor = readableDatabase.rawQuery("SELECT * FROM Users WHERE Username = \"$username\" AND Password = \"$password\"", arrayOf())
        if(cursor.count == 0) {
            return false
        }
        cursor.moveToFirst()


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

    fun addFunds(user: User, amount: Double){
        val columns = ContentValues()
        val funds = verifyUser(user.username, user.password)!!.funds
        var newAmount = funds + amount
        newAmount = BigDecimal(newAmount).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        Log.d("DBController", "new amount: $newAmount")
        Log.d("DBController", "user username: ${user.username}")
        Log.d("DBController", "user password: ${user.password}")
        Log.d("DBController", "user name: ${user.name}")
        Log.d("DBController", "user funds: ${user.funds}")
        columns.put("Funds", newAmount)
        user.funds = newAmount
        writableDatabase.update("Users", columns, "Username = \"${user.username}\"", arrayOf())
        Log.d("DBController", "user funds: ${user.funds}")

    }

    fun deductFunds(user: User, amount: Double){
        val columns = ContentValues()
        val funds = verifyUser(user.username, user.password)!!.funds
        var newAmount = funds - amount
        newAmount = BigDecimal(newAmount).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        Log.d("DBController", "user funds: $funds")
        columns.put("Funds", newAmount)
        user.funds = newAmount
        writableDatabase.update("Users", columns, "Username = \"${user.username}\"", arrayOf())
        Log.d("DBController", "user funds: ${user.funds}")
    }

    fun changePassword(username: String, password: String, newPassword: String) {
        val columns = ContentValues()
        val db = this.writableDatabase
        columns.put("Password", newPassword)


        Log.d("DBController", "rows affected: ${db.update("Users", columns, "Username = \"$username\" AND Password = \"$password\"", arrayOf())}")
    }


    /**
     * Orders DB
     */

    fun createOrder(username: String, date: String, title: String, seller: String, platform: String, type: String, description: String, price: Double, image: Int): Boolean{
        val columns = ContentValues()
        columns.put("Username", username)
        columns.put("Date", date)
        columns.put("Title", title)
        columns.put("Seller", seller)
        columns.put("Platform", platform)
        columns.put("Type", type)
        columns.put("Description", description)
        columns.put("Price", price)
        columns.put("Image", image)
        writableDatabase.insert("Orders", null, columns)
        return true
    }

    fun orderOrdersByDate(user: User): List<Order> {

        val cursor = readableDatabase.rawQuery("SELECT * FROM Orders WHERE Username = \"${user.username}\" ORDER BY Date ASC", arrayOf())
        val listOfOrders = mutableListOf<Order>()
        while(cursor.moveToNext()) {
            val order = Order(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),   cursor.getString(6),  cursor.getString(7), cursor.getDouble(8), cursor.getInt(9))
            listOfOrders.add(order)
        }

        return listOfOrders

    }

}