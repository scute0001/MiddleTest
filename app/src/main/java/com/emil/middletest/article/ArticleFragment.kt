package com.emil.middletest.article

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emil.middletest.R
import com.emil.middletest.databinding.ArticleFragmentBinding
import com.emil.middletest.home.HomeFragmentViewModelFactory
import com.emil.middletest.home.HomeViewModel

class ArticleFragment : DialogFragment() {

    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ArticleFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val viewModelFactory = ArticalViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticleViewModel::class.java)
        binding.viewModel = viewModel


        viewModel.submitData.observe(viewLifecycleOwner, Observer {data ->
            if (data == null) {
                Toast.makeText(requireContext(), "輸入的欄位有缺喔", Toast.LENGTH_SHORT).show()
            } else {
                Log.i("data","data is $data")
                viewModel.submitToFireStore(data)
            }
        })

        binding.buttonSubmit.setOnClickListener {
            viewModel.setArticle()
        }




        return binding.root
    }


}