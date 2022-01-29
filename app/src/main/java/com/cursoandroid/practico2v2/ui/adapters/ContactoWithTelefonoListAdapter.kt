package com.cursoandroid.practico2v2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.practico2v2.dal.models.ContactoWithTelefono
import com.cursoandroid.practico2v2.databinding.ContactoListItemBinding
import com.cursoandroid.practico2v2.ui.fragments.MainFragmentDirections

class ContactoWithTelefonoListAdapter(
    var listaContactoWithTelefono: List<ContactoWithTelefono>
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ContactoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val objContactoWithTelefono = listaContactoWithTelefono[position]
        val objContacto = objContactoWithTelefono.contacto
        holder.binding.lbNombreContactoItem.text = "${objContacto.nombres} ${objContacto.apellido}"
        val objTelefono = objContactoWithTelefono.telefonos
        if (!objTelefono.isEmpty()) {
            holder.binding.lbNroContactoItem.text = "${objTelefono.first().numero}"
        } else {
            holder.binding.lbNroContactoItem.text = "${"[Sin numero]"}"
        }
        holder.binding.rowLayoutContacto.setOnClickListener {
            val action =
                MainFragmentDirections.actionDetailContacto(currentContactoWithTelefono = objContactoWithTelefono)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return listaContactoWithTelefono.size
    }


    fun setData(listaContactoWithTelefono: ArrayList<ContactoWithTelefono>) {
        this.listaContactoWithTelefono = listaContactoWithTelefono
    }


}


class ViewHolder(val binding: ContactoListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {}
