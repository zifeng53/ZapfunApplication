package com.example.zapfunfoodorderingapplication.models

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class OrderStatusModel(
    @SerializedName("order_status")
    var orderStatus: String? = null,
    @SerializedName("headlines")
    var paperHeadlines: List<OrderItemModel>? = null,
    @SerializedName("is_expanded")
    var isExpanded: Boolean? = false
) : Serializable {
    override
    fun toString(): String {
        return GsonBuilder().serializeNulls().create().toJson(this)
    }
}