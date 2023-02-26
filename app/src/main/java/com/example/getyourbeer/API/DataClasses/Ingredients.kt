package com.example.getyourbeer.API.DataClasses

import com.google.gson.annotations.SerializedName


data class Ingredients (

    @SerializedName("malt"  ) var malt  : ArrayList<Malt> = arrayListOf(),
    @SerializedName("hops"  ) var hops  : ArrayList<Hops> = arrayListOf(),
    @SerializedName("yeast" ) var yeast : String?         = null

)

data class Malt (
    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("amount" ) var amount : Amount? = Amount()
)

data class Amount (
    @SerializedName("value" ) var value : Double? = null,
    @SerializedName("unit"  ) var unit  : String? = null
)

data class Hops (
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("amount"    ) var amount    : Amount? = Amount(),
    @SerializedName("add"       ) var add       : String? = null,
    @SerializedName("attribute" ) var attribute : String? = null
)