package com.example.zapfunfoodorderingapplication.models

class MenuDetailModel {
    var name:String?=null
    var image:String?=null
    var price: String?=null

    constructor()
    constructor(name: String?, image: String?, price: String?) {
        this.name = name
        this.image = image
        this.price = price
    }
}