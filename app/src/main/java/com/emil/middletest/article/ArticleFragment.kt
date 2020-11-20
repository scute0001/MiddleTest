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
import androidx.navigation.fragment.findNavController
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

//        viewModel.userCheck.observe(viewLifecycleOwner, Observer {
//            if (it == true) {
//                binding.buttonSubmit.isEnabled = true
//            } else {
//                binding.buttonSubmit.isEnabled = false
//                Toast.makeText(requireContext(), "目前沒有使用者資訊", Toast.LENGTH_SHORT).show()
//            }
//        })


        viewModel.submitData.observe(viewLifecycleOwner, Observer {data ->
            if (data == null) {
                Toast.makeText(requireContext(), "輸入的欄位有缺喔", Toast.LENGTH_SHORT).show()
            } else {
                Log.i("data","data is $data")
                viewModel.submitToFireStore(data)
            }
        })

        viewModel.submitDataFinished.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Toast.makeText(requireContext(), "新增資料成功", Toast.LENGTH_SHORT).show()
                findNavController().navigate(ArticleFragmentDirections.actionArticleFragmentToHomeFragment())
                viewModel.submitToFireStoreFinished()
            }
        })


        binding.buttonSubmit.setOnClickListener {
            viewModel.setArticle()
        }




        return binding.root
    }


}