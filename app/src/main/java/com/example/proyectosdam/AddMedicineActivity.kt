package com.example.proyectosdam

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectosdam.databinding.ActivityAddMedicineBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddMedicineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMedicineBinding
    private val db = Firebase.firestore
    private lateinit var paciente: Paciente
    private lateinit var medicamento: Medicamento

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // reccoger bundle paciente

        val bundle = intent.extras
        paciente = bundle!!["myUser"] as Paciente
        binding.nombre.text = paciente.nombre

        binding.cbDesayuno.setOnClickListener {
            //    binding.txtPastillasDesayuno.visibility=View.VISIBLE
            //     binding.txtPastillasDesayuno.text="Pastillas: 1"
            //   binding.txtCantPastillasD.visibility=View.VISIBLE

        }


        binding.btnLimpiar.setOnClickListener {

       //     limpiar()

            instanciarMedicina()
        }

        binding.btnGuardar.setOnClickListener {
            Log.e("GUARDANDO", "--------------------- " + paciente.sip.toString())
       //     instanciarMedicina()
            guardarMedicina()
        }

    }

    private fun instanciarMedicina() {
        val nombre: String = binding.txtMedicine.text.toString()
        val cantidad: Int = binding.txtNumComprimidos.text.toString().toInt()
        val dosisDesayuno: Double = binding.txtPastillasDesayuno.text.toString().toDouble()
        val dosisComida: Double = binding.txtPastillasComida.text.toString().toDouble()
        val dosisCena: Double = binding.txtPastillasCena.text.toString().toDouble()
        val dosisResopon: Double = binding.txtPastillasResopon.text.toString().toDouble()


        medicamento =
            Medicamento(nombre, cantidad, dosisDesayuno, dosisComida, dosisCena, dosisResopon)
        Log.e("GUARDANDO",
            "----medicamento---- $nombre-$cantidad+$dosisDesayuno+$dosisComida+$dosisCena+$dosisResopon"
        )
        Log.e("GUARDANDO", "----medicamento---- " + medicamento.nombre+"-"+ medicamento.numComprimidos+"+")

    }

    private fun guardarMedicina() {
        /*
        val medicina = hashMapOf<String, Any?>()
        medicina["nombre"] = binding.txtMedicine.text
        medicina["cantidad"] = binding.txtNumComprimidos.text
        val messageRef = db
            .collection("users").document(paciente.sip!!.toString())
            .collection("medicines").document(medicina["nombre"].toString()).set(medicina)
   */
        val medicina = hashMapOf<String, Medicamento?>()
        medicamento.nombre?.let { medicina.put(it, medicamento) }
        val messageRef = db
            .collection("users").document(paciente.sip!!.toString())
            .collection("medicines").document(medicamento.nombre!!).set(medicamento)

    }

    private fun limpiar() {
        binding.txtMedicine.setText("")
        binding.txtNumComprimidos.setText("")
        binding.cbDesayuno.isChecked = false
        binding.cbComida.isChecked = false
        binding.cbCena.isChecked = false
        binding.cbResopon.isChecked = false
    }
}