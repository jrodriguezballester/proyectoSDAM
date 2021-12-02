package com.example.proyectosdam

import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AlertDialog

class Recursos {
    // validaciones
    public fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //^ # inicio-de-cadena
    //(? =. * [0-9]) # un dígito debe aparecer al menos una vez
    //(? =. * [a-z]) # una letra minúscula debe aparecer al menos una vez
    //(? =. * [A-Z]) # una letra mayúscula debe aparecer al menos una vez
    //(? =. * [@ # $% ^ & + =]) # un carácter especial debe aparecer al menos una vez que pueda reemplazarlo con sus caracteres especiales
    // (? = \\ S + $) # no se permiten espacios en blanco en toda la cadena
    //. {4,} # . cualquier caracter,{n} n lugares,{n,m} n=min, m=max
    //$ # final de cadena
    public fun isValidPassword(password: String): Boolean {
        //minimo un numero,minuscula y masyuscula y 8 digitos
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"
        val pattern = Regex(PASSWORD_PATTERN)
        return pattern.containsMatchIn(password)
    }

    public fun mostrarAviso2(contex: Context) {

        AlertDialog.Builder(contex).setTitle("Titulo del diálogo")
            .setMessage("Contenido del diálogo.").setPositiveButton(
                android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                }).setNegativeButton(
                android.R.string.cancel,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón cancel pulsado
                }).show()

    }

    public fun mostrarAviso1(contex: Context, titulo: String, mensaje: String) {
        AlertDialog.Builder(contex).setTitle(titulo).setMessage(mensaje).setPositiveButton(
                android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                }).show()
    }

    public fun debugLista(list: List<Medicina>) {
        Log.e(ContentValues.TAG, "longitud lista medicamentos ::${list.size} ")
        list.forEach { medicamento ->
            Log.e(
                ContentValues.TAG, "Lista::${medicamento.nombre} => ${medicamento.numComprimidos}"
            )
        }
    }
    fun debugMedicina(medicina: Medicina?) {
        Log.e(ContentValues.TAG, "Objeto recogido ::${medicina?.nombre} => ${medicina?.numComprimidos}")
    }
}