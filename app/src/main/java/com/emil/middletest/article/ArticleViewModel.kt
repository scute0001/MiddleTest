package com.emil.middletest.article

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.emil.middletest.database.Author
import com.emil.middletest.database.PublishData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap

class ArticleViewModel(app: Application) : AndroidViewModel(app){
    var _title: String = ""
    var _category: String = ""
    var _content: String = ""
    val authorInfo = Author("Emil@gmail.com", "Emil0001", "EMil")

    // Access a Cloud Firestore instance from your Activity
    val db = FirebaseFirestore.getInstance()

   init {

   }

    private val _submitData = MutableLiveData<PublishData>()
    val submitData: LiveData<PublishData>
        get() = _submitData

    private val _submitDataFinished = MutableLiveData<Boolean>()
    val submitDataFinished: LiveData<Boolean>
        get() = _submitDataFinished


    private val _userCheck = MutableLiveData<Boolean>()
    val userCheck: LiveData<Boolean>
        get() = _userCheck

    init {
        _submitDataFinished.value = null
        _userCheck.value = null
    }


    fun checkUser(user: Author) {
        if (user.name != "" && user.email != "" && user.id != "") {
            _userCheck.value = true
        }
    }

    fun setArticle() {

        if ( _title != "" && _content != "" && _category != "" ) {
            val article = PublishData(
                author = authorInfo,
                category = _category,
                content = _content,
                title = _title,
                id = ""
            )
            _submitData.value = article
        } else {
            _submitData.value = null
        }
    }

    fun submitToFireStore(publishData: PublishData) {
        viewModelScope.launch {
            db.collection("articles")
                .add(publishData)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
                    documentReference.update("id", documentReference.id)

                    //submit success and set navigation to home
                    _submitDataFinished.value = true
                }
        }
    }

    fun submitToFireStoreFinished() {
        _submitDataFinished.value = null
        _submitData.value = null
    }

//    fun test(db: FirebaseFirestore) {
//        val data = hashMapOf("author" to hashMapOf("email" to "wayne@school.appworks.tw",
//            "id" to "waynechen323", "name" to "AKA小安老師"),
//            "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場",
//            "content" to "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言自己品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭造型，卻意外美出新境界。",
//            "createdTime" to Calendar.getInstance().timeInMillis,
//            "id" to "","category" to "Beauty")
//
//        db.collection("articles")
//            .add(data as HashMap<String, Any>)
//            .addOnSuccessListener { documentReference ->
//                Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
//                documentReference.update("id", documentReference.id)
//            }
//    }

}