package com.example.proyectosdam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Paciente:Serializable {



    @SerializedName("nombre")
    @Expose
    var nombre: String? = null
    @SerializedName("sip")
    @Expose
    var sip: String? = null


    constructor(nombre: String?, sip: String?) {
        this.nombre = nombre
        this.sip = sip
    }

    constructor()

}