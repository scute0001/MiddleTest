package com.emil.middletest.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PublishData(
    var author: Author,
    var category: String,
    var content: String,
    var createdTime: Long,
    var id: String = "",
    var title: String
)
{

}
