package com.example.proyectosdam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_medicina.view.*

class MedicinaAdapter(private val medicinas: List<Medicina>) :
    RecyclerView.Adapter<MedicinaAdapter.MedicinaHolder>() {
    class MedicinaHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(medicina: Medicina) {
            view.tvMedicina.text = medicina.nombre
            view.tvDesayuno.text = medicina.dosisDesayuno.toString()
            view.tvComida.text = medicina.dosisComida.toString()
            view.tvCena.text = medicina.dosisCena.toString()
            view.tvResopon.text = medicina.dosisResopon.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicinaHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MedicinaHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_medicina, parent, false))
    }

    override fun onBindViewHolder(holder: MedicinaHolder, position: Int) {
        holder.render(medicinas[position])
    }

    override fun getItemCount(): Int {
        return medicinas.size
    }
}