package com.emil.middletest.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

data class PublishData(
    var author: Author,
    var category: String,
    var content: String,
    var createdTime: Long = Calendar.getInstance().timeInMillis,
    var id: String = "",
    var title: String
)
{

}
