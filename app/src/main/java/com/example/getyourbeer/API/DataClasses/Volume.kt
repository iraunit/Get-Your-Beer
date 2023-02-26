package com.example.getyourbeer.API.DataClasses

import com.google.gson.annotations.SerializedName

data class Volume (

    @SerializedName("value" ) var value : Double?    = null,
    @SerializedName("unit"  ) var unit  : String? = null

)