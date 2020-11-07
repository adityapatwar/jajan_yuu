package com.nmproduction.jajanyuu.data.model.profile

import com.google.gson.annotations.SerializedName
import com.nmproduction.jajanyuu.data.model.product.Product

data class Profile(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("noHandPhone")
    var noHandPhone: String?

){
    constructor() : this("","","",""){

    }

}