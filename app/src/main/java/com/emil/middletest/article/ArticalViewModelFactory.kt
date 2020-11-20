package com.emil.middletest.article

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emil.middletest.home.HomeViewModel
import java.lang.IllegalArgumentException

class ArticalViewModelFactory(private val application: Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)){
            return ArticleViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
