package com.donly.funny.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.donly.funny.databinding.FragmentSearchBinding

class SearchBarFragment: Fragment() {
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        return binding.root
    }



    private inner class EventHandler: View.OnClickListener {

        override fun onClick(v: View?) {

        }

    }

}
