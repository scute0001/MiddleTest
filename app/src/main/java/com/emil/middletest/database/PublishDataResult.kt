package com.emil.middletest.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PublishDataResult(
    val data: PublishData?
): Parcelable
