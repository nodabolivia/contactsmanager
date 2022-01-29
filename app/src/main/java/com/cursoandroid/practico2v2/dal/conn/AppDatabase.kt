package com.cursoandroid.practico2v2.dal.conn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cursoandroid.practico2v2.dal.dao.ContactoDao
import com.cursoandroid.practico2v2.dal.dao.TelefonoDao
import com.cursoandroid.practico2v2.dal.models.Contacto
import com.cursoandroid.practico2v2.dal.models.Telefono


@Database(entities = [Contacto::class,Telefono::class ], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactoDao(): ContactoDao
    abstract fun telefonoDao(): TelefonoDao
    companion object{
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "practico"
                ).allowMainThreadQueries()
                    .build()
            }
            return  INSTANCE!!
        }
    }
}

