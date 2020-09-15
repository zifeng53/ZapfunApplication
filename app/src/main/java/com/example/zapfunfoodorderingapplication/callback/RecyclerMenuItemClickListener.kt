package com.example.zapfunfoodorderingapplication.callback

import android.view.View

interface RecyclerMenuItemClickListener {
    fun onItemClick(view: View, pos:Int)
}