package com.emil.middletest.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val email: String = "",
    val id: String = "",
    val name: String = ""
): Parcelable

//    "author" to hashMapOf(
//        "email" to "wayne@school.appworks.tw",
//        "id" to "waynechen323",
//        "name" to "AKA小安老師"
//    ),