package com.example.gapp

class Image {
    var imagePath : String?=null
    var imageName : String?=null

    constructor(imagePath: String?,imageName:String?){
        this.imageName = imageName
        this.imagePath = imagePath
    }
}