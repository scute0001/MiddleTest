package com.emil.middletest.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emil.middletest.database.Author
import com.emil.middletest.database.PublishData
import com.google.firebase.firestore.FirebaseFirestore
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

                data.forEach {
                    val temp = it.data
                    val author = it.data.get("author") as HashMap<String, String>

                    val tempPublishData = PublishData(
                        author = Author(name = author.get("name"), email = author.get("email"), id = author.get("id")),
                        title = temp.get("title") as String,
                        content = temp.get("content") as String,
                        createdTime = temp.get("createdTime") as Long,
                        id = temp.get("id") as String,
                        category = temp.get("category") as String
                    )
                    articleList.add(tempPublishData)

                }
                _articleListData.value = articleList
            }

    }

}