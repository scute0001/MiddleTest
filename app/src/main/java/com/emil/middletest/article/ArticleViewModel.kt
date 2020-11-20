package com.emil.middletest.article

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emil.middletest.database.Author
import com.emil.middletest.database.PublishData

class ArticleViewModel(app: Application) : AndroidViewModel(app){
    var _title: String = ""
    var _category: String = ""
    var _content: String = ""

    private val _submitData = MutableLiveData<PublishData>()
    val submitData: LiveData<PublishData>
        get() = _submitData



    fun setArticle() {

        if ( _title != "" && _content != "" && _category != "" ) {
            val article = PublishData(
                author = Author("Emil@gmail.com", "Emil0001", "EMil"),
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

}