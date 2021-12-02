package com.example.proyectosdam


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectosdam.databinding.ActivityListMedicineBinding
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list_medicine.*


class ListMedicineActivity : AppCompatActivity() {
    private lateinit var medicinasRef: CollectionReference
    private val db = Firebase.firestore

    private lateinit var paciente: Paciente
    private lateinit var miList: RecyclerView
    private lateinit var binding: ActivityListMedicineBinding
    private var medicinas: List<Medicina> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMedicineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //   miList = binding.miReciclerView

        // reccoger bundle paciente
        val bundle = intent.extras
        paciente = bundle!!["myUser"] as Paciente
        //  binding.nombre.text = paciente.nombre

        // Referencia en BD
        medicinasRef =
            db.collection("users").document(paciente.sip!!.toString()).collection("medicines")

        // Rellenar lista medicinas
        rellenarListaMedicinas()
        // Se llama a initRecicler cuando carga la lista

    }

    private fun rellenarListaMedicinas() {
        Log.e(TAG, "RellenarMedicinas")
        medicinasRef.get().addOnSuccessListener { documents ->
            var docSnapshots = documents.documents
            for (i in docSnapshots) {
                var medicina = i.toObject<Medicina>()
                medicinas += medicina!!
                Recursos().debugMedicina(medicina)
            }
            Recursos().debugLista(medicinas)

            initRecycler()

        }.addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
    }

    fun initRecycler() {
        Log.e(TAG, "initRecicler")
        rvMedicinas.layoutManager
        rvMedicinas.layoutManager = LinearLayoutManager(this)
        val adapter = MedicinaAdapter(medicinas)
        rvMedicinas.adapter = adapter
    }


}








