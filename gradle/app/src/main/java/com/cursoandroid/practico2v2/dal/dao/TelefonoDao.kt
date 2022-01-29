package com.cursoandroid.practico2v2.dal.dao

import androidx.room.*
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.dal.models.Telefono

@Dao
interface TelefonoDao {
    @Query("SELECT * FROM telefono_table")
    fun getAll():List<Telefono>

    @Query("SELECT * FROM telefono_table WHERE idContacto = :idContacto")
    fun getAllByContacto(idContacto:Int):List<Telefono>

    @Query("SELECT * FROM telefono_table WHERE id = :id")
    fun getById(id:Int): Telefono

    @Insert
    fun insert(vararg  telefono: Telefono)

    @Update
    fun update(telefono: Telefono)

    @Delete
    fun delete(telefono: Telefono)


}