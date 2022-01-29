package com.cursoandroid.practico2v2.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.practico2v2.R
import com.cursoandroid.practico2v2.dal.conn.AppDatabase
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.dal.models.Telefono
import com.cursoandroid.practico2v2.databinding.FragmentDetalleContactoBinding
import com.cursoandroid.practico2v2.ui.adapters.TelefonoListAdapter


class DetalleContactoFragment : Fragment() {
    private val args by navArgs<DetalleContactoFragmentArgs>()
    private lateinit var currentContacto: Contacto
    private lateinit var listTelefono: ArrayList<Telefono>

    private lateinit var adapter: TelefonoListAdapter
    private var _binding: FragmentDetalleContactoBinding? = null
    private val binding get() = _binding!!
    private lateinit var db:AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalleContactoBinding.inflate(inflater, container, false)
        val view = binding.root
        db= AppDatabase.getInstance(requireContext())
        args.let {
            currentContacto = it.currentContactoWithTelefono!!.contacto
            listTelefono = it.currentContactoWithTelefono!!.telefonos as ArrayList<Telefono>
        }
        loadDataContacto()
        setupEventListener()
        setupRecyclerView()
        setHasOptionsMenu(true)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupEventListener() {
        binding.btnEditContacto.setOnClickListener {
            setupActionUpdateContacto()
        }
        binding.btnAddTelefono.setOnClickListener{
            setupActionAddTelefono()
        }
    }
    private fun setupRecyclerView(){
        adapter = TelefonoListAdapter(listTelefono)
        val linearLayoutV = LinearLayoutManager(requireContext())
        binding.lstTelefonos.adapter = adapter
        binding.lstTelefonos.layoutManager = linearLayoutV
    }
    override fun onResume() {
        super.onResume()
        setDataContacto(db.contactoDao().getById(args.currentContactoWithTelefono!!.contacto.id))
        loadDataContacto()
        listTelefono = db.telefonoDao().getAllByContacto(args.currentContactoWithTelefono!!.contacto.id) as ArrayList<Telefono>
        adapter.setData(listTelefono)
    }

    private fun loadDataContacto(){
        //currentContacto = args.currentContactoWithTelefono!!.contacto
        binding.lbNombre.text = "${currentContacto.nombres} ${currentContacto.apellido}"
        binding.lbCiudad.text = "${currentContacto.ciudad}"
        binding.lbDireccion.text = "${currentContacto.direccion}"
        binding.lbEdad.text = "${currentContacto.edad.toString()}"
        binding.lbEmail.text = "${currentContacto.email}"
    }
    private fun setupActionUpdateContacto(){
        val action = DetalleContactoFragmentDirections.actionUpdateContacto(currentContacto)
        findNavController().navigate(action)
    }
    private fun setupActionAddTelefono(){
        val action = DetalleContactoFragmentDirections.actionAddTelefono(currentContacto)
        findNavController().navigate(action)
    }
    fun setDataContacto(currentContacto: Contacto ){
        this.currentContacto = currentContacto
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete ){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteUser(){
        db.contactoDao().delete(currentContacto)
        findNavController().popBackStack()
    }
    private fun deleteUserAdvance(){
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Si"){_,_ ->
            db.contactoDao().delete(currentContacto)
            Toast.makeText(
                requireContext(),
                "Contacto exitosamente eliminado: ${currentContacto.nombres}",Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("¿Deseas eliminar ${currentContacto.nombres}?")
        builder.setMessage("¿Estas seguro que deseas eliminar ${currentContacto.nombres}?")
        builder.create().show()
    }



}