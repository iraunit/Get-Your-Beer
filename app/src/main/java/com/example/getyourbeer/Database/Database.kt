package com.shyptsolution.getyourbeer.Database

import android.content.Context
import androidx.room.*
import androidx.room.Database

@Database(
    entities = [
        BeerEntity::class
    ],
    version = 1
)
@TypeConverters(TypeConverterClass::class)
abstract class Database :RoomDatabase(){

    abstract fun getDao(): DAO

    companion object {
        @Volatile
        private var instance: com.shyptsolution.getyourbeer.Database.Database? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance =it
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
           com.shyptsolution.getyourbeer.Database.Database::class.java,
            "my_database"
        ).build()
    }

}