package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.dal.models.Telefono
import com.cursoandroid.practico2v2.databinding.FragmentFormTelefonoBinding
import com.cursoandroid.practico2v2.databinding.FragmentFormTelefonoUpdateBinding


class FormTelefonoUpdateFragment : Fragment() {
    private val args by navArgs<FormTelefonoUpdateFragmentArgs>()
    private lateinit var db: AppDatabase
    private lateinit var tipo: String
    private lateinit var currentTelefono: Telefono

    private var _binding: FragmentFormTelefonoUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormTelefonoUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        db = AppDatabase.getInstance(requireContext())
        args.let {
            currentTelefono = it.currentTelefono!!
        }
        loadDataTelefono()
        setupEventListener()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener() {
        binding.btnActualizarTelefono.setOnClickListener {
            updateDatatoDatabase()
        }

        binding.radiogroupUpdate.setOnCheckedChangeListener(
            { group, checkedId ->
                val radio: RadioButton = binding.root.findViewById<RadioButton>(checkedId)
                if (radio.equals(binding.rbPersonalizadoUpdate)) {
                    tipo = "${binding.txtPersonalizadoUpdate.text}"
                } else {
                    tipo = "${radio.text.toString()}"
                }
            }
        )


        binding.rbPersonalizadoUpdate.setOnClickListener {
            binding.txtPersonalizadoUpdate.isEnabled = true
        }
        binding.rbCasaUpdate.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }
        binding.rbCelularUpdate.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }
        binding.rbOficinaUpdate.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }
        binding.rbTrabajoUpdate.setOnClickListener {
            disableAndCleanTxtPersonalizado()
        }

    }

    private fun loadDataTelefono(){
        currentTelefono = args.currentTelefono
        binding.txtTelefonoUpdate.setText(currentTelefono.numero.toString())


    }
    private fun updateDatatoDatabase(){
        currentTelefono = args.currentTelefono
        val id = currentTelefono.id
        val idContacto = currentTelefono.idContacto
        val numero = binding.txtTelefonoUpdate.editableText.toString()
        if (binding.rbPersonalizadoUpdate.isChecked) {
            tipo = "${binding.txtPersonalizadoUpdate.text}"
        }
        if (inputCheckEmpty( tipo)) {
            val telefono = Telefono(id,Integer.parseInt(idContacto.toString()),Integer.parseInt(numero.trim()), tipo)
            db.telefonoDao().update(telefono)
            Toast.makeText(requireContext(),"Telefono actualizado", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()

        } else {
            Toast.makeText(requireContext(), "Rellenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }


    private fun disableAndCleanTxtPersonalizado() {
        binding.txtPersonalizadoUpdate.text.clear()
        binding.txtPersonalizadoUpdate.isEnabled = false
    }

    private fun inputCheckEmpty(

        tipo: String
    ): Boolean {
        return !(TextUtils.isEmpty(tipo) )
    }
}