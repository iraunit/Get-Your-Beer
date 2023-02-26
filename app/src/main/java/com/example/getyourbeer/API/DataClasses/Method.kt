package com.example.getyourbeer.API.DataClasses

import com.google.gson.annotations.SerializedName

data class Method (
    @SerializedName("mash_temp"    ) var mashTemp     : ArrayList<MashTemp> = arrayListOf(),
    @SerializedName("fermentation" ) var fermentation : Fermentation?       = Fermentation(),
    @SerializedName("twist"        ) var twist        : String?             = null
)

data class MashTemp (
    @SerializedName("temp"     ) var temp     : Temp? = Temp(),
    @SerializedName("duration" ) var duration : Int?  = null
)

data class Temp (
    @SerializedName("value" ) var value : Double?    = null,
    @SerializedName("unit"  ) var unit  : String? = null
)

data class Fermentation (
    @SerializedName("temp" ) var temp : Temp? = Temp()
)