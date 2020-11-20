package com.emil.middletest.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emil.middletest.database.Author
import com.emil.middletest.database.PublishData
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class HomeViewModel(app: Application) : AndroidViewModel(app) {



    val _articleListData = MutableLiveData<List<PublishData>>()
    val articleListData: LiveData<List<PublishData>>
        get() = _articleListData

    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()

    init {
        _articleListData.value = null
        getDataFromFireStore(db)
    }

    fun getDataFromFireStore(db: FirebaseFirestore) {
        var articleList = mutableListOf<PublishData>()

        db.collection("articles").get()
            .addOnSuccessListener { data ->
                Log.i("dateTemp","dateTemp is ${data.documents}")

//                articleList = data.toObjects(PublishData::class.java)
                data.forEach {
                    val temp = it.data

                    val tempPublishData = PublishData(
                        author = Author(),
                        title = temp.get("title") as String,
                        content = temp.get("content") as String,
                        createdTime = temp.get("createdTime") as Long,
                        id = temp.get("id") as String,
                        category = temp.get("category") as String
                    )

                    articleList.add(tempPublishData)
                    Log.i("temp","tempPublishData is $tempPublishData")
                }
//                Log.i("temp","articleList is $articleList")
                _articleListData.value = articleList
//                Log.i("1","articleList is ${_articleListData.value}")
            }

    }

    fun test(db: FirebaseFirestore) {
        val data = hashMapOf("author" to hashMapOf("email" to "wayne@school.appworks.tw",
            "id" to "waynechen323", "name" to "AKA小安老師"),
            "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場",
            "content" to "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言自己品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭造型，卻意外美出新境界。",
            "createdTime" to Calendar.getInstance().timeInMillis,
            "id" to "","category" to "Beauty")

        db.collection("articles")
            .add(data as HashMap<String, Any>)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
                documentReference.update("id", documentReference.id)
            }
    }


}