package com.example.proyectosdam


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectosdam.databinding.ActivityListMedicineBinding


class ListMedicineActivity : AppCompatActivity() {
    private lateinit var miList: RecyclerView
    private lateinit var binding: ActivityListMedicineBinding
    private val names: ArrayList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMedicineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        miList = binding.miReciclerView

        
    }
}