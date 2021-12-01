package com.example.proyectosdam

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectosdam.databinding.ActivityAddMedicineBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddMedicineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMedicineBinding
    private val db = Firebase.firestore
    private lateinit var paciente: Paciente
    private lateinit var medicamento: Medicamento

    private var nombre = ""
    private var cantidad: Int = 0
    private var dosisDesayuno: Double = 0.0
    private var dosisComida: Double = 0.0
    private var dosisCena: Double = 0.0
    private var dosisResopon: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMedicineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // reccoger bundle paciente
        val bundle = intent.extras
        paciente = bundle!!["myUser"] as Paciente
        binding.nombre.text = paciente.nombre

        // Inicializar valores
        resetDesayuno()
        resetComida()
        resetCena()
        resetResopon()
        /*    binding.txtPastillasDesayuno.setText("0")
            binding.txtPastillasComida.setText("0")
            binding.txtPastillasCena.setText("0")
            binding.txtPastillasResopon.setText("0")
    */
        binding.cbDesayuno.setOnClickListener {
            if (binding.cbDesayuno.isChecked) {
                binding.tvDesayuno.visibility = View.VISIBLE
                binding.txtPastillasDesayuno.visibility = View.VISIBLE
                binding.txtPastillasDesayuno.setText("1")
            } else {
                resetDesayuno()
            }
        }
        binding.cbComida.setOnClickListener {
            if (binding.cbComida.isChecked) {
                binding.tvComida.visibility = View.VISIBLE
                binding.txtPastillasComida.visibility = View.VISIBLE
                binding.txtPastillasComida.setText("1")
            } else {
                resetComida()
            }
        }
        binding.cbCena.setOnClickListener {
            if (binding.cbCena.isChecked) {
                binding.tvCena.visibility = View.VISIBLE
                binding.txtPastillasCena.visibility = View.VISIBLE
                binding.txtPastillasCena.setText("1")
            } else {
                resetCena()
            }
        }
        binding.cbResopon.setOnClickListener {
            if (binding.cbResopon.isChecked) {
                binding.tvResopon.visibility = View.VISIBLE
                binding.txtPastillasResopon.visibility = View.VISIBLE
                binding.txtPastillasResopon.setText("1")
            } else {
                resetResopon()
            }
        }

        binding.btnLimpiar.setOnClickListener {
            limpiar()
        }

        binding.btnGuardar.setOnClickListener {
            Log.e("btnGuardar", "--- " + paciente.sip.toString())
            instanciarMedicina()
            guardarMedicina()
            limpiar()
        }
    }

    private fun instanciarMedicina() {
        // TODO FIX evitar cuadros em blanco
        nombre = binding.txtMedicine.text.toString()
        cantidad = binding.txtNumComprimidos.text.toString().toInt()
        dosisDesayuno = binding.txtPastillasDesayuno.text.toString().toDouble()
        dosisComida = binding.txtPastillasComida.text.toString().toDouble()
        dosisCena = binding.txtPastillasCena.text.toString().toDouble()
        dosisResopon = binding.txtPastillasResopon.text.toString().toDouble()
        Log.e(
            "Datos Recogidos",
            "----medicamento---- $nombre-$cantidad+$dosisDesayuno+$dosisComida+$dosisCena+$dosisResopon"
        )

        medicamento =
            Medicamento(nombre, cantidad, dosisDesayuno, dosisComida, dosisCena, dosisResopon)

        Log.e(
            "instanciarMedicina",
            "----medicamento---- " + medicamento.nombre + "-" + medicamento.numComprimidos
        )

    }

    private fun guardarMedicina() {
        val medicina = hashMapOf<String, Medicamento?>()
        medicamento.nombre?.let { medicina.put(it, medicamento) }
        db.collection("users").document(paciente.sip!!.toString())
            .collection("medicines").document(medicamento.nombre!!.toString()).set(medicamento)
        Log.e(
            "GUARDARMEDICINA",
            "----medicamento---- " + medicamento.nombre + "-" + medicamento.numComprimidos
        )
    }

    private fun limpiar() {
        // TODO LIMPIAR BIEN
        binding.txtMedicine.setText("")
        binding.txtNumComprimidos.setText("")

        binding.cbDesayuno.isChecked = false
        binding.cbComida.isChecked = false
        binding.cbCena.isChecked = false
        binding.cbResopon.isChecked = false

        resetDesayuno()
        resetComida()
        resetCena()
        resetResopon()
    }

    private fun resetResopon() {
        binding.tvResopon.visibility = View.INVISIBLE
        binding.txtPastillasResopon.visibility = View.INVISIBLE
        binding.txtPastillasResopon.setText("0")
    }

    private fun resetCena() {
        binding.tvCena.visibility = View.INVISIBLE
        binding.txtPastillasCena.visibility = View.INVISIBLE
        binding.txtPastillasCena.setText("0")
    }

    private fun resetComida() {
        binding.tvComida.visibility = View.INVISIBLE
        binding.txtPastillasComida.visibility = View.INVISIBLE
        binding.txtPastillasComida.setText("0")
    }

    private fun resetDesayuno() {
        binding.tvDesayuno.visibility = View.INVISIBLE
        binding.txtPastillasDesayuno.visibility = View.INVISIBLE
        binding.txtPastillasDesayuno.setText("0")
    }

}