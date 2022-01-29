package com.cursoandroid.practico2v2.dal.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName= "contacto_table")
data class Contacto(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val nombres:String?,
    val apellido:String?,
    @ColumnInfo(name="url_foto",defaultValue = "img") val urlfoto:String?,
    val ciudad:String?,
    val edad:Int?,
    val email:String?,
    val direccion:String?

):Parcelable
