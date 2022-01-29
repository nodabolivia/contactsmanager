package com.cursoandroid.practico2v2.dal.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "telefono_table")
data class Telefono(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val idContacto: Int,
    val numero: Int,
    val tipo: String
):Parcelable
