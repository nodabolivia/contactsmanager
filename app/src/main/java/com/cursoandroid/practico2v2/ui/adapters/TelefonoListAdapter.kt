package com.cursoandroid.practico2v2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.practico2v2.dal.models.Telefono
import com.cursoandroid.practico2v2.databinding.TelefonoListItemBinding
import com.cursoandroid.practico2v2.ui.fragments.DetalleContactoFragmentDirections

class TelefonoListAdapter(
    var listaTelefono: List<Telefono>
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            TelefonoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val objTelefono = listaTelefono[position]
        holder.binding.lbTipoTelefonoItem.text = "${objTelefono.tipo}"
        holder.binding.lbNroTelefonoItem.text = "${objTelefono.numero}"
        holder.binding.rowLayoutTelefono.setOnClickListener {
            val action =
                DetalleContactoFragmentDirections.actionDetailTelefono( currentTelefono = objTelefono)
            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return listaTelefono.size
    }

    fun setData(listaTelefono: List<Telefono>){
        this.listaTelefono=listaTelefono
    }

}

class MyViewHolder(val binding: TelefonoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
}