package com.example.myproject

data class Product(var name: String, var road: String, var menu: String, var region: String, var tel: String) {
    constructor():this("noInfo","noInfo","noInfo", "noInfo", "noInfo")
}