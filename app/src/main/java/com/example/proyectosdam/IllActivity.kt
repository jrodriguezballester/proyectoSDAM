package com.example.proyectosdam

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectosdam.databinding.ActivityIllBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class IllActivity : AppCompatActivity() {
    private var myid: String = ""
    private lateinit var binding: ActivityIllBinding
    val db = Firebase.firestore
    val mRootReference = FirebaseDatabase.getInstance()
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIllBinding.inflate(layoutInflater)
        val view = binding.root
        // val sip = binding.SIP.text.toString()
        // val illName = binding.nameIll.text.toString()

        setContentView(view)

        binding.registerIllButton.setOnClickListener {
            val illName = binding.nameIll.text.toString()
            val sip = binding.SIP.text.toString()

            // Create a new usuario with a nick and SIP
            val user = hashMapOf(
                "nick" to illName,
                "SIP" to sip
            )
            val myUsuario:Paciente=Paciente()

            myUsuario.nombre=illName
            myUsuario.sip=sip


            val usuario = hashMapOf<String, Any?>()
            usuario["nick"] = illName
            usuario["SIP"] = sip
            // guarda usuario en BD
            db.collection("users").document(sip).set(usuario)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "REGISTRADO",
                        Toast.LENGTH_SHORT
                    ).show()
                    this.continuarApp(usuario,myUsuario)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }


        binding.AccederButton.setOnClickListener {
            val sip = binding.SIP.text.toString()
            db.collection("users").document(sip).get().addOnSuccessListener {
                // con esto tenemos el it
                Toast.makeText(
                    this,
                    it.getString("nick"),
                    Toast.LENGTH_SHORT
                ).show();
                continuar(it, sip)
            }
        }
    }

    private fun continuar(it: DocumentSnapshot?, sip: String) {
        val usuario = hashMapOf<String, Any?>()
        usuario["medicina"] = "illName"
        usuario["SIP"] = "sip"
     //TODO continuar en otro activity que muestre las dosis

    //    db.collection("users").document(sip)
    //        .collection("Medicinas").add(usuario)
    }


    public fun continuarApp(usuario: HashMap<String, Any?>, myUsuario: Paciente) {
    //    Recursos().mostrarAviso1(this, "Aviso", "Continua la aplicacion:$myid")

        //     val enfermo = db.collection("users").document(myid)
        // Set the "medicina" field of the city 'DC'
        //    enfermo.update("medicina", "valium")
        //          .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
        //          .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

        val bundle = Bundle()
        bundle.putSerializable("myUser", usuario)
        bundle.putSerializable("myUser2", myUsuario)

        val intent = Intent(this, AddMedicineActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}

//  val enfermo = db.collection("users").document(sip)
//  enfermo.update("medicina", "valium")