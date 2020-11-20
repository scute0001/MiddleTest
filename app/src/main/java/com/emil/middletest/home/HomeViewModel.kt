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

}