package com.example.zapfunfoodorderingapplication.models

class MenuChickenModel {
    var menu_id:String?=null
    var name:String?=null
    var image:String?=null

    constructor()
    constructor(menu_id: String?, name: String?, image: String?) {
        this.menu_id = menu_id
        this.name = name
        this.image = image
    }
}