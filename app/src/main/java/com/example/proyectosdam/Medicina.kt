package com.example.proyectosdam

data class Medicina(val nombre: String="",
                    val numComprimidos: Int=0,
                    val dosisDesayuno: Double=0.0,
                    val dosisComida: Double=0.0,
                    val dosisCena: Double=0.0,
                    val dosisResopon: Double=0.0){
    constructor() : this(nombre="",0,0.0,0.0,0.0,0.0)
}

