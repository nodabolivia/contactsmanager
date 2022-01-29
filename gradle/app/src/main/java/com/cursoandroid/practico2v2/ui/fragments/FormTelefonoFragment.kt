package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.dal.models.Telefono
import com.cursoandroid.practico2v2.databinding.FragmentFormTelefonoBinding
import kotlinx.android.synthetic.main.fragment_form_telefono.*


class FormTelefonoFragment : Fragment() {
    private val args by navArgs<FormTelefonoFragmentArgs>()
    private lateinit var db: AppDatabase
    private lateinit var tipo: String
    private lateinit var currentContacto: Contacto

    private var _binding: FragmentFormTelefonoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormTelefonoBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentContacto = it.currentContacto!!
           }
        setupEventListener()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener() {
        binding.btnAgregarTelefono.setOnClickListener {
            insertDatatoDatabase()
        }

        binding.radiogroup.setOnCheckedChangeListener(
            { group, checkedId ->
                val radio: RadioButton = binding.root.findViewById<RadioButton>(checkedId)
                if (radio.equals(binding.rbPersonalizado)) {
                    tipo = "${binding.txtPersonalizado.text}"
                } else {
                    tipo = "${radio.text.toString()}"
                }
            }
        )


        binding.rbPersonalizado.setOnClickListener {
            binding.txtPersonalizado.isEnabled = true
        }
        binding.rbCasa.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }
        binding.rbCelular.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }
        binding.rbOficina.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }
        binding.rbTrabajo.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }

    }


    private fun insertDatatoDatabase() {
        val numero = binding.txtTelefono.editableText.toString()
        if (binding.rbPersonalizado.isChecked) {
            tipo = "${binding.txtPersonalizado.text}"
        }
        if (inputCheckEmpty( tipo)) {
            val telefono = Telefono(0, Integer.parseInt(currentContacto.id.toString()),Integer.parseInt(numero.trim()),tipo)
            db.telefonoDao().insert(telefono)
            Toast.makeText(requireContext(),"Telefono registrado",Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()

        } else {
            Toast.makeText(requireContext(), "Rellenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun disableAndCleanTxtPersonalizado() {
        binding.txtPersonalizado.text.clear()
        binding.txtPersonalizado.isEnabled = false
    }

    private fun inputCheckEmpty(
        tipo: String
    ): Boolean {
        return !(TextUtils.isEmpty(tipo) )
    }


}