package com.shyptsolution.getyourbeer.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "beer_details")
data class BeerEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    var name: String? = null,
    var tagline: String? = null,
    var firstBrewed: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var attenuationLevel: Double? = null,
    var food_pairing: ArrayList<String>? = null,
    var price: Double? = null,
    var favorite: Boolean = false,
    var inCart:Boolean = false,
    var quantity: Int? = 0
)

class TypeConverterClass {
    @TypeConverter
    fun fromList(list: ArrayList<String>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}