package com.example.zapfunfoodorderingapplication.utils

import android.content.Context
import com.example.zapfunfoodorderingapplication.models.OrderItemListHeaderModel
import com.google.gson.Gson
import org.json.JSONObject

class JsonHelper2(private var context: Context) {
    private var orderheaderlist: MutableList<OrderItemListHeaderModel>? = null

    open fun getListData(): List<OrderItemListHeaderModel>?{
        if (orderheaderlist == null)
            orderheaderlist = ArrayList()

        try {
            val jsonObject = JSONObject(getJSONFromAssets("test_data2.json"))
            val jsonArray = jsonObject.getJSONArray("order_list")
            val k = jsonArray.length()

            for (i in 0 until k) {
                val tempJsonObject = jsonArray.getJSONObject(i).toString()
                val gson = Gson()
                val newsPaper = gson.fromJson<OrderItemListHeaderModel>(tempJsonObject, OrderItemListHeaderModel::class.java)
                orderheaderlist?.add(newsPaper)
            }
            return orderheaderlist
        } catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }

    private fun getJSONFromAssets(fileName: String): String? {
        val json: String
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }
}