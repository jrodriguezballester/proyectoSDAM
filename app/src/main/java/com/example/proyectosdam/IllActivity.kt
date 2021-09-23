package com.example.proyectosdam

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectosdam.databinding.ActivityIllBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IllActivity : AppCompatActivity() {
    private var myid: String = ""
    private lateinit var binding: ActivityIllBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIllBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerIllButton.setOnClickListener {
            val illName = binding.nameIll.text.toString()
            val sip = binding.SIP.text.toString()
            /////
            Toast.makeText(
                this,
                "pulsado",
                Toast.LENGTH_SHORT
            ).show();
            /////
            //     val db = Firebase.firestore
            // Create a new user with a first and last name
            val user = hashMapOf(
                "nick" to illName,
                "SIP" to sip,
                "born" to 1815
            )
// Add a new document with a generated ID
            db.collection("users").add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    myid = documentReference.id
                    //  continuarApp()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
        binding.AccederButton.setOnClickListener {
            continuarApp()
        }
    }


    public fun continuarApp() {
        Recursos().mostrarAviso1(this, "Aviso", "Continua la aplicacion:$myid")

        val enfermo = db.collection("users").document(myid)
    // Set the "medicina" field of the city 'DC'
        enfermo.update("medicina", "valium")
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

        //   val intent = Intent(this, IllActivity::class.java)
        //   startActivity(intent)
    }
}