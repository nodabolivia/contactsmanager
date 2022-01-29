package com.cursoandroid.practico2v2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.dal.models.ContactoWithTelefono
import com.cursoandroid.practico2v2.databinding.FragmentMainBinding
import com.cursoandroid.practico2v2.ui.adapters.ContactoWithTelefonoListAdapter


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ContactoWithTelefonoListAdapter
    private lateinit var listContactoWithTelefono: ArrayList<ContactoWithTelefono>
    private lateinit var db:AppDatabase



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        db= AppDatabase.getInstance(requireContext())
        loadBaseData()
        setupEventListener()
        setupRecyclerView()
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        listContactoWithTelefono = db.contactoDao().getContactosWithTelefonos() as ArrayList<ContactoWithTelefono>
        adapter.setData(listContactoWithTelefono)
    }
    private fun setupEventListener() {
        binding.btnAddContacto.setOnClickListener {
            findNavController().navigate(R.id.actionAddContacto)
        }
    }
    private fun setupRecyclerView(){
        adapter = ContactoWithTelefonoListAdapter(listContactoWithTelefono)
        val linearLayoutV = LinearLayoutManager(requireContext())
        binding.lstContactos.adapter = adapter
        binding.lstContactos.layoutManager = linearLayoutV
    }
    private  fun loadBaseData(){
        listContactoWithTelefono = db.contactoDao().getContactosWithTelefonos() as ArrayList<ContactoWithTelefono>
    }



}