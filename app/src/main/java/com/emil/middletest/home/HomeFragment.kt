package com.emil.middletest.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emil.middletest.databinding.HomeFragmentBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = HomeFragmentBinding.inflate(inflater, container , false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val viewModelFactory = HomeFragmentViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        //adapter
        val adapter = PublishAdapter(viewModel)
        binding.recyclerPublish.adapter = adapter

        viewModel.articleListData.observe(viewLifecycleOwner, Observer {
            Log.i("ITTTTT", "it $it")
            adapter.submitList(it)
        })







        return binding.root
    }

}