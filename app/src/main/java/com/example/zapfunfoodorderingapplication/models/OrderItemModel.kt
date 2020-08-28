package com.example.zapfunfoodorderingapplication.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OrderItemModel(
    @SerializedName("recipient_name")
    var recipient: String? = null,
    @SerializedName("total_amount")
    var amount: String? = null,
    @SerializedName("order_item")
    var item_details: String? = null,
    @SerializedName("order_date")
    var order_date: String? = null

) : Serializable