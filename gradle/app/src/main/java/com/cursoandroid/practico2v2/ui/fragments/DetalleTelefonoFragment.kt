package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.dal.models.Telefono
import com.cursoandroid.practico2v2.databinding.FragmentDetalleTelefonoBinding


class DetalleTelefonoFragment : Fragment() {
    private val args by navArgs<DetalleTelefonoFragmentArgs>()
    private lateinit var currenTelefono: Telefono
    private var _binding: FragmentDetalleTelefonoBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleTelefonoBinding.inflate(inflater, container, false)
        val view = binding.root
        db= AppDatabase.getInstance(requireContext())
        args.let {
            currenTelefono = it.currentTelefono!!
        }
        loadDataTelefono()
        setupEventListener()
        return view
      }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        setDataTelefono(db.telefonoDao().getById(args.currentTelefono!!.id))
        loadDataTelefono()
    }



    private fun setupEventListener() {
        binding.btnEditTelefono.setOnClickListener {
            setupActionUpdateTelefono()
        }
        }
    private fun loadDataTelefono(){
        binding.lbNroTelefono.text = "${currenTelefono.numero}"
        binding.lbTipoTelefono.text = "${currenTelefono.tipo}"
    }

    private fun setupActionUpdateTelefono(){
        val action = DetalleTelefonoFragmentDirections.actionUpdateTelefono(currenTelefono)
        findNavController().navigate(action)
    }
    fun setDataTelefono(currenTelefono: Telefono){
        this.currenTelefono = currenTelefono
    }



    }

