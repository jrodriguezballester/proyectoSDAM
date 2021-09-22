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
    private lateinit var binding: ActivityIllBinding

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
            val db = Firebase.firestore
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
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}