package com.example.zapfunfoodorderingapplication.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OrderItemListModel (
    @SerializedName("item_detail")
    var item_detail: String? = null
) : Serializable