package com.example.proyectosdam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Medicamento : Serializable {

    @SerializedName("nombre")
    @Expose
    var nombre: String? = null

    @SerializedName("numComprimidos")
    @Expose
    var numComprimidos: Int? = null

    @SerializedName("dosis")
    @Expose
    var dosis: List<Double> = emptyList()

    constructor(
        nombre: String?,
        numComprimidos: Int?,
        dosisDesayuno: Double,
        dosisComida: Double,
        dosisCena: Double,
        dosisResopon: Double
    ) {
        this.nombre = nombre
        this.numComprimidos = numComprimidos
        this.dosis = listOf(dosisDesayuno, dosisComida, dosisCena, dosisResopon)
    }

    constructor()
}