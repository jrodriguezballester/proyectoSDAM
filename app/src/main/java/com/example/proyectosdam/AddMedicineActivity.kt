package com.example.proyectosdam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectosdam.databinding.ActivityAddMedicineBinding

class AddMedicineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMedicineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // reccoger bundle paciente
        val bundle = intent.extras
        val paciente:Paciente= bundle!!["myUser2"] as Paciente
        binding.nombre.text = paciente.nombre

    }
}