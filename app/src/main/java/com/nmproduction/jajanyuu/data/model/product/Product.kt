package com.nmproduction.jajanyuu.data.model.product

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    var id: String,
    @SerializedName("imageMakanan")
    var imageMakanan: String,
    @SerializedName("namaMakanan")
    val namaMakanan: String,
    @SerializedName("hargaMakanan")
    val hargaMakanan: String,
    @SerializedName("namaPenjualMakanan")
    var namaPenjualMakanan: String?

){
    constructor() : this("","","","",""){

    }

}