package com.example.zapfunfoodorderingapplication.models

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OrderItemListHeaderModel (
    @SerializedName("order_qty")
    var qty: String? = null,
    @SerializedName("headlines")
    var listHeadlines: List<OrderItemListModel>? = null,
    @SerializedName("order_price")
    var price: String? = null,
    @SerializedName("is_expanded")
    var isExpanded: Boolean? = false
) : Serializable {
    override
    fun toString(): String {
        return GsonBuilder().serializeNulls().create().toJson(this)
    }
}