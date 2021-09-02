package com.donly.funny.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.donly.funny.R
import com.donly.funny.databinding.FragmentShortFilmsBinding


class ShortFilmsFragment : Fragment() {
    companion object {
        const val TAG = "ShortFilmsFragment"
    }

    lateinit var binding: FragmentShortFilmsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShortFilmsBinding.inflate(inflater, container, false)
        binding.handler = ShortFilmsHandler()
        return binding.root
    }


    inner class ShortFilmsHandler : View.OnClickListener {

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_search -> {
                    val action = MainFragmentDirections.actionMainFragmentToSearchBarFragment()
                    findNavController().navigate(action)
                }
                else -> {
                    Log.d(TAG, "onClick: " + v?.id)
                }
            }
        }

    }


}