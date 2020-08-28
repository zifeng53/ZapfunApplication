package com.example.zapfunfoodorderingapplication.utils

import android.content.Context
import com.example.zapfunfoodorderingapplication.models.OrderStatusModel
import com.google.gson.Gson
import org.json.JSONObject

open class JsonHelper(private var context: Context) {
    private var orderstatuslist: MutableList<OrderStatusModel>? = null

    open fun getHistoryData(): List<OrderStatusModel>? {
        if (orderstatuslist == null)
            orderstatuslist = ArrayList()

        try {
            val jsonObject = JSONObject(getJSONFromAssets("test_data.json"))
            val jsonArray = jsonObject.getJSONArray("order_history")
            val k = jsonArray.length()

            for (i in 0 until k) {
                val tempJsonObject = jsonArray.getJSONObject(i).toString()
                val gson = Gson()
                val newsPaper = gson.fromJson<OrderStatusModel>(tempJsonObject, OrderStatusModel::class.java)
                orderstatuslist?.add(newsPaper)
            }
            return orderstatuslist
        } catch (e: Exception) {
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