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
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.databinding.FragmentFormContactoBinding


class FormContactoFragment : Fragment() {
    private lateinit var db: AppDatabase
    private var _binding: FragmentFormContactoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormContactoBinding.inflate(inflater, container, false)
        val view = binding.root

        db = AppDatabase.getInstance(requireContext())
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setupEventListener() {
        binding.btnAgregarContacto.setOnClickListener {
                insertDatatoDatabase()
        }


    }
    private fun insertDatatoDatabase(){
        val nombres= binding.txtNombre.text.toString()
        val apellidos = binding.txtApellido.text.toString()
        val direccion = binding.txtDireccion.text.toString()
        val email = binding.txtEmail.text.toString()
        val edad= binding.txtEdad.text
        val ciudad = binding.txtCiudad.text.toString()
        if(inputCheckEmpty(nombres,apellidos,direccion, email, ciudad, edad)){
            val contacto = Contacto(0,nombres,apellidos,"",ciudad,Integer.parseInt(edad.toString()),email,direccion)
            db.contactoDao().insert(contacto)
            Toast.makeText(requireContext(),"Contacto registrado",Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()

        }else{
            Toast.makeText(requireContext(),"Rellenar todos los campos",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheckEmpty(
        nombres:String,apellidos:String,direccion:String, email:String,ciudad:String,edad:Editable
        ): Boolean{
        return !(TextUtils.isEmpty(nombres) &&TextUtils.isEmpty(apellidos) &&TextUtils.isEmpty(direccion) &&TextUtils.isEmpty(email) &&TextUtils.isEmpty(ciudad) && edad.isEmpty())
    }



}