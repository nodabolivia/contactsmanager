package com.cursoandroid.practico2v2.dal.dao

import androidx.room.*
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.dal.models.ContactoWithTelefono

@Dao
interface ContactoDao {
    @Query("SELECT * FROM contacto_table")
    fun getAll():List<Contacto>

    @Query("SELECT * FROM contacto_table WHERE id = :id")
    fun getById(id:Int): Contacto

    @Insert
    fun insert(vararg  contacto: Contacto)

    @Update
    fun update(contacto: Contacto)

    @Delete
    fun delete(contacto: Contacto)

    @Transaction
    @Query("SELECT * FROM contacto_table")
    fun getContactosWithTelefonos():List<ContactoWithTelefono>

}