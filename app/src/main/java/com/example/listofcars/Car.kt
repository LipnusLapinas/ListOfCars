package com.example.listofcars

class Car(val id: Int, val plate: String, val lat: Float, val lon: Float, val bat: Int, val imageURL: String) {
    constructor (id: Int, plate: String, lat: Int, lon: Int, bat: Int, imageURL: String):
            this(id, plate, lat.toFloat(), lon.toFloat(), bat, imageURL)

    constructor (id: Int, plate: String, lat: Double, lon: Double, bat: Int, imageURL: String):
            this(id, plate, lat.toFloat(), lon.toFloat(), bat, imageURL)
}


