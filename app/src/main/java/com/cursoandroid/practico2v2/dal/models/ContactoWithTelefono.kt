package com.cursoandroid.practico2v2.dal.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactoWithTelefono(
    @Embedded val contacto: Contacto,
    @Relation(
        parentColumn = "id",
        entityColumn = "idContacto"
    )
    val telefonos:List<Telefono>
):Parcelable
