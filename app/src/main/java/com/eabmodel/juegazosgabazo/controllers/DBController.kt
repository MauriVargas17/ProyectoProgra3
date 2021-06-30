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

class DBController(context: Context): SQLiteOpenHelper(context, "Users", null, 9)  {

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
        createProduct(db,"Grand Theft Auto V", "Raul_Sells", "Play Station", "Disk", "When a young street hustler, a retired bank robber and a terrifying psychopath find themselves entangled with some of the most frightening and deranged elements of the criminal underworld, the U.S. government and the entertainment industry, they must pull off a series of dangerous heists to survive in a ruthless city in which they can trust nobody, least of all each other", 12.99, R.drawable.por16)
        createProduct(db,"Call of Duty Black Ops: Cold War", "Muchos_Juegos", "Play Station", "Disk", "The iconic Black Ops series is back with Call of Duty®: Black Ops Cold War on PS4 - the direct sequel to the original and fan-favorite Call of Duty®: Black Ops. CoD Black Ops Cold War will drop fans into the depths of the Cold War’s volatile geopolitical battle of the early 1980s. Nothing is ever as it seems in a gripping single-player Campaign, where players will come face-to-face with historical figures and hard truths, as they battle around the globe through iconic locales like East Berlin, Vietnam, Turkey, Soviet KGB headquarters and more", 59.99, R.drawable.por17)
        createProduct(db,"Fifa 21", "paulRey", "Xbox Live", "Key", "Win as one in EA SPORTS™ FIFA 21 on PlayStationⓇ4, Xbox One, and PC with new ways to team up and express yourself on the street and in the stadium. Powered by Frostbite™, FIFA 21 raises the game with fresh features that let you enjoy even bigger victories together in VOLTA FOOTBALL and FIFA Ultimate Team™, feel a new level of gameplay realism that rewards you for your creativity and control, manage every moment in Career Mode, and experience unrivaled authenticity that gives you the most true-to-life experience of The World’s Game.", 19.99, R.drawable.por18)
        createProduct(db,"Assassin's Creed Valhalla", "paulRey", "Xbox Live", "Disk", "Become Eivor, a legendary Viking raider on a quest for glory. Explore England's Dark Ages as you raid your enemies, grow your settlement, and build your political power.", 59.99, R.drawable.por19)
        createProduct(db, "Borderlands 3", "Tienda_Jueguitos", "Steam", "Key", "The original shooter-looter returns, packing bazillions of guns and an all-new mayhem-fueled adventure! Blast through new worlds and enemies as one of four brand new Vault Hunters - the ultimate treasure-seeking badasses of the Borderlands, each with deep skill trees, abilities and customization. Play solo or join with friends to take on insane enemies, score loads of loot and save your home from the most ruthless cult leaders in the galaxy.", 10.00, R.drawable.por20)
        createProduct(db,"Red Dead Redemption 2", "SamGames", "Play Station", "Key", "Arthur Morgan and the Van der Linde gang are outlaws on the run. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive. As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own ideals and loyalty to the gang who raised him.", 39.99, R.drawable.por21)
        createProduct(db,"Cyberpunk", "ParyGameplays", "Xbox Live", "Key", "Cyberpunk 2077 on PS4 is an open-world, action-adventure story set in Night City, a megalopolis obsessed with power, glamour and body modification. You play as V, a mercenary outlaw going after a one-of-a-kind implant that is the key to immortality. You can customize your character's cyberware, skillset and playstyle, and explore a vast city where the choices you make shape the story and the world around you.", 23.99, R.drawable.por22)
        createProduct(db,"NBA 2K21", "Vernard", "Xbox Live", "Key", "NBA 2K21 is the latest release in the world-renowned, best-selling NBA 2K series. With exciting improvements upon its best-in-class gameplay, competitive and community online features, and deep, varied game modes, NBA 2K21 offers one-of-a-kind immersion into all facets of NBA basketball and culture - where Everything is Game.", 15.00, R.drawable.por23)
        createProduct(db, "Call of Duty: Modern Warfare", "Top_Games", "Play Station", "Disk", "Prepare to go dark, Call of Duty: Modern Warfare is back on PS4! The stakes have never been higher as players take on the role of lethal Tier One operators in a heart-racing saga that will affect the global balance of power. Call of Duty: Modern Warfare engulfs fans in an incredibly raw, gritty, provocative narrative that brings unrivaled intensity and shines a light on the changing nature of modern war. Developed by the studio that started it all, Infinity Ward delivers an epic reimagining of the iconic Modern Warfare series from the ground up.", 39.99, R.drawable.por24)
        createProduct(db, "Overwatch", "Muchos_Juegos", "Steam", "Key", "Clash on the battlefields of tomorrow and choose your hero from a diverse cast of soldiers, scientists, adventurers, and oddities. Bend time, defy physics, and unleash an array of extraordinary powers and weapons. Engage your enemies in iconic locations from around the globe in the ultimate team-based shooter. Take your place in Overwatch. The world needs heroes.", 19.99, R.drawable.por25)
        createProduct(db, "Mortal Kombat 11", "Raul_Sells", "Play Station", "Disk", "Mortal Kombat 11 is the latest installment in the critically acclaimed franchise, providing a deeper and more personalized experience than ever before with an all new Custom Character Variation System that gives players the creative control to customize versions of the entire character roster. Developed by award-winning NetherRealm Studios, Mortal Kombat 11 will introduce a new cinematic story continuing the epic saga that is more than 25 years in the making.", 19.00, R.drawable.por26)
        createProduct(db, "Watch Dogs: Legion", "swaglord", "Play Station", "Disk", "Build a resistance made from anyone in the world to take back a near-future London that is facing its downfall. Recipient of over 65 E3 awards and nominations.", 16.99, R.drawable.por27)
        createProduct(db, "Dark Souls III", "Manuelito34", "Xbox Live","Key", "The DARK SOULS™ III: THE FIRE FADES™ EDITION delivers the complete Dark Souls™ III experience and includes the full game & all Season Pass content – ASHES OF ARIANDEL™ and THE RINGED CITY™ DLC expansions. Winner of 2017 DICE Awards for RPG/Massively Multiplayer Game of the Year” and the 2016 Golden Joystick Award's “Ultimate Game of the Year”, Dark Souls III continues to push the boundaries with the latest chapter in the critically-acclaimed and genre-defining series from director Hidetaka Miyazaki. Players will be immersed into a world of epic atmosphere and darkness with colossal enemies, expansive environments, and challenging and intense gameplay combat. As fires fade and the world falls into ruin, prepare yourself once more to Embrace The Darkness!", 13.99, R.drawable.por28)
        createProduct(db,"Rust", "Topurizo", "Steam", "Key", "The only aim in Rust is to survive. Everything wants you to die - the island’s wildlife and other inhabitants, the environment, other survivors. Do whatever it takes to last another night.", 19.99, R.drawable.por29)
        createProduct(db,"Madden 21", "AdriPeris", "Play Station", "Key", "Put your skills to the test with Madden NFL 21 on PS4! A new generation of players are leaving their mark on the NFL. Will you rise to the occasion? Change the game and take control of your own legacy.", 19.99, R.drawable.por30)
        createProduct(db,"Death Stranding", "Muchos_Juegos", "Steam", "Key", "From legendary game creator Hideo Kojima comes an all-new, genre-defying exerience. Sam Bridges must brave a world utterly transformed by the Death Stranding. Carrying the disconnected remnants of our future in his hands, he embarks on a journey to reconnect the shattered world one step at a time.", 39.99, R.drawable.por35)
        createProduct(db,"Super Mario Odyssey ", "Juegitos_Julio", "Nintendo", "Cartridge", "Explore incredible places far from the Mushroom Kingdom as you join Mario and his new ally Cappy on a massive, globe-trotting 3D adventure. Use amazing new abilities—like the power to capture and control objects, animals, and enemies—to collect Power Moons so you can power up the Odyssey airship and save Princess Peach from Bowser’s wedding plans!", 59.99, R.drawable.por36)
        createProduct(db,"God of War", "GamePlanet", "Play Station", "Disk", "From Santa Monica Studio and creative director Cory Barlog comes a new beginning for one of gaming’s most recognizable icons. Living as a man outside the shadow of the gods, Kratos must adapt to unfamiliar lands, unexpected threats, and a second chance at being a father. Together with his son Atreus, the pair will venture into the brutal Norse wilds and fight to fulfill a deeply personal quest.", 49.99, R.drawable.por33)
        createProduct(db,"Metal Gear Solid V: The Phantom Pain", "G2A", "Steam", "Key", "The Soviet invasion of Afghanistan has brought a new edge to the Cold War, and in 1984, a one-eyed man with a prosthetic arm appears in the country. Those who know him call him Snake; the legendary mercenary who was once swept from the stage of history and left in a coma by American private intelligence network Cipher. Snake is accompanied by Ocelot, an old friend who saved him from attack when he finally awoke. Now, Snake's former partner Kazuhira Miller is being held by the Soviet forces in Afghanistan. Snake must undertake a solo mission to rescue Miller and prove to the world that the legendary mercenary is not dead and gone. That first step will lead to a path of vengeance against the very Cipher that slaughtered so many of Snake's men, and to a battle that will embroil the whole world...What started in Ground Zeroes must finish with V ", 59.99, R.drawable.por34)
        createProduct(db,"The Legend of Zelda™: Breath of the Wild", "Muchos_Juegos", "Nintendo", "Cartridge", "Forget everything you know about The Legend of Zelda games. Step into a world of discovery, exploration, and adventure in The Legend of Zelda: Breath of the Wild, a boundary-breaking new game in the acclaimed series. Travel across vast fields, through forests, and to mountain peaks as you discover what has become of the kingdom of Hyrule in this stunning Open-Air Adventure", 59.99, R.drawable.por31)
        createProduct(db,"Gears 5", "G2A", "Xbox Live", "Disk", "From one of gaming’s most acclaimed sagas, Gears 5’s celebrated campaign and refreshed multiplayer are optimized with new features for the Xbox Series X|S update. Campaign: With all-out war descending, Kait Diaz breaks away to uncover her connection to the enemy and discovers the true danger to Sera – herself. New campaign features let you take your character and weapon skins into new playthroughs and enjoy bonus difficulties and modifiers", 29.99, R.drawable.por32)
        createProduct(db,"Xbox™ Series X", "Microsoft Store", "Xbox Live", "Console", "The Xbox Series X delivers sensationally smooth frame rates of up to 120FPS with the visual pop of HDR. Immerse yourself with sharper characters, brighter worlds, and impossible details with true-to-life 4K", 499.99, R.drawable.por9)
        createProduct(db,"Resident Evil™ Village", "Juegitos_Julio", "Xbox Live", "Disk", "Experience survival horror like never before in Resident Evil village, the eighth major instalment in the genre-defining Resident Evil franchise. Set a few years after the horrifying events in the critically acclaimed Resident Evil 7 biohazard, the all-new storyline brings Ethan Winters to a remote snow-capped village filled with a diverse cast of terrifying enemies. After a devastating encounter with Resident Evil series hero Chris Redfield, Ethan pursues him in search of answers but finds himself in an entirely new nightmare", 59.99, R.drawable.por8)
        createProduct(db,"Xbox™ Series X", "Microsoft Store", "Xbox Live", "Console", "The Xbox Series X delivers sensationally smooth frame rates of up to 120FPS with the visual pop of HDR. Immerse yourself with sharper characters, brighter worlds, and impossible details with true-to-life 4K", 499.99, R.drawable.por9)
        createProduct(db,"Spider-Man", "GameStop", "Play Station", "Disk", "An all-new Spider-Man experience Starring one of the world’s most iconic Super Heroes, Spider-Man features the acrobatic abilities, improvisation and web-slinging that the wall-crawler is famous for, while also introducing elements never-before-seen in a Spider-Man game. From traversing with parkour and unique environmental interactions, to new combat and cinematic blockbuster set pieces, it’s Spider-Man unlike any you’ve played before. Sony Interactive Entertainment, Insomniac Games, and Marvel have teamed up to create a brand-new and authentic Spider-Man adventure. This isn’t the Spider-Man you’ve met before, or seen in a movie. This is an experienced Peter Parker who’s more masterful at fighting big crime in New York City. At the same time, he’s struggling to balance his chaotic personal life and career while the fate of millions of New Yorkers rest upon his shoulders", 49.99, R.drawable.por7)
        createProduct(db,"Minecraft", "Muchos_Juegos", "Xbox Live", "Disk", "Minecraft is a game about placing blocks and going on adventures. Explore randomly generated worlds and build amazing things from the simplest of homes to the grandest of castles. Play in Creative Mode with unlimited resources or in Survival Mode, defend against monsters and dig deep into the world to discover the rarest ores. Do all this alone, or work together with your friends over the internet. Build, create, and explore!.", 29.99, R.drawable.por10)
        createProduct(db,"Nintendo™ Switch", "Muchos_Juegos", "Nintendo", "Console", "Introducing Nintendo Switch, the new home video game system from Nintendo. In addition to providing single and multiplayer thrills at home, the Nintendo Switch system can be taken on the go so players can enjoy a full home console experience anytime, anywhere. The mobility of a handheld is now added to the power of a home gaming system, with unprecedented new play styles brought to life by the two new Joy-Con controllers. PLAY ANYWHERE", 299.99, R.drawable.por11)
        createProduct(db,"Pokemon™ Sword/Shield", "GamePlanet", "Nintendo", "Cartridge", "A new generation of Pokémon is coming to the Nintendo Switch™ system. Begin your adventure as a Pokémon Trainer by choosing one of three new partner Pokémon: Grookey, Scorbunny, or Sobble. Then embark on a journey in the new Galar region, where you’ll challenge the troublemakers of Team Yell, while unraveling the mystery behind the Legendary Pokémon Zacian and Zamazenta! Explore the Wild Area, a vast expanse of land where the player can freely control the camera. Team up with three other players locally or online in the new multiplayer co-op Max Raid Battles* in which players will face off against gigantic and super-strong Pokémon known as Dynamax Pokémon.", 59.99, R.drawable.por12)
        createProduct(db,"Uncharted 4", "GameStop", "Play Station", "Disk", "Several years after his last adventure, retired fortune hunter, Nathan Drake, is forced back into the world of thieves. With the stakes much more personal, Drake embarks on a globe-trotting journey in pursuit of a historical conspiracy behind a fabled pirate treasure. His greatest adventure will test his physical limits, his resolve, and ultimately what he’s willing to sacrifice to save the ones he loves", 19.99, R.drawable.por13)
        createProduct(db,"Star Wars™ Battlefront 2", "G2A",  "Steam", "Key", "Embark on an endless Star Wars™ journey from the best-selling Star Wars™ video game franchise of all time. Experience rich multiplayer battlegrounds across all three eras - prequel, classic and new trilogy - or rise as a new hero and discover an emotionally gripping single-player story spanning thirty years. Customize and upgrade your heroes, starfighters or troopers - each with unique abilities to exploit in battle. Ride tauntauns or take control of tanks and speeders. Use the Force to prove your worth against iconic characters like Kylo Ren, Darth Maul or Han Solo, as you play a part in a gaming experience inspired by forty years of timeless Star Wars™ films", 59.99, R.drawable.por14)
        createProduct(db,"Halo™: The Master Chief Collection", "Muchos_Juegos", "Xbox Live", "Disk", "The Master Chief’s iconic journey includes six games, built for Xbox One and collected in a single integrated experience. Whether you’re a long-time fan or meeting Spartan 117 for the first time, The Master Chief Collection is the definitive Halo gaming experience.", 39.99, R.drawable.por15)



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