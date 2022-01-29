package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.databinding.FragmentFormContactoBinding
import com.cursoandroid.practico2v2.databinding.FragmentFormContactoUpdateBinding
import kotlinx.android.synthetic.main.fragment_form_contacto_update.*

class FormContactoUpdateFragment : Fragment() {
    private var _binding: FragmentFormContactoUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private val args by navArgs<FormContactoUpdateFragmentArgs>()
    private lateinit var currentContacto: Contacto


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormContactoUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        loadDataContacto()
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener(){
        binding.btnActualizarContacto.setOnClickListener {
            updateDatatoDatabase()
        }
    }


    private fun loadDataContacto(){
        currentContacto = args.currentContacto
        binding.txtNombreUpdate.setText(currentContacto.nombres)
        binding.txtApellidoUpdate.setText(currentContacto.apellido)
        binding.txtCiudadUpdate.setText(currentContacto.ciudad)
        binding.txtDireccionUpdate.setText(currentContacto.direccion)
        binding.txtEdadUpdate.setText(currentContacto.edad.toString())
        binding.txtEmailUpdate.setText(currentContacto.email)
    }
    private fun updateDatatoDatabase(){
        currentContacto = args.currentContacto
        val id = args.currentContacto.id
        val nombres= binding.txtNombreUpdate.text.toString()
        val apellidos = binding.txtApellidoUpdate.text.toString()
        val direccion = binding.txtDireccionUpdate.text.toString()
        val email = binding.txtEmailUpdate.text.toString()
        val edad= binding.txtEdadUpdate.text
        val ciudad = binding.txtCiudadUpdate.text.toString()
        if(inputCheckEmpty(nombres,apellidos,direccion, email, ciudad, edad)){
            val contacto = Contacto(id,nombres,apellidos,"",ciudad,Integer.parseInt(edad.toString()),email,direccion)
            db.contactoDao().update(contacto)
            Toast.makeText(requireContext(),"Contacto actualizado", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }else{
            Toast.makeText(requireContext(),"Rellenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheckEmpty(
        nombres:String,apellidos:String,direccion:String, email:String,ciudad:String,edad: Editable
    ): Boolean{
        return !(TextUtils.isEmpty(nombres) && TextUtils.isEmpty(apellidos) && TextUtils.isEmpty(direccion) && TextUtils.isEmpty(email) && TextUtils.isEmpty(ciudad) && edad.isEmpty())
    }
}