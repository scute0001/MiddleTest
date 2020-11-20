package com.emil.middletest.database

import java.util.*


class PublishDataResult(
    var author: Map<String, String>?,
    var category: String?,
    var content: String?,
    var createdTime: Long = Calendar.getInstance().timeInMillis,
    var id: String = "",
    var title: String?
)
{

}