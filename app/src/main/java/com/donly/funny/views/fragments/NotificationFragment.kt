package com.donly.funny.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.donly.funny.R
import com.donly.funny.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {
    lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        binding.handler = NotificationHandler()
        return binding.root
    }


    inner class NotificationHandler : View.OnClickListener {

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_search -> {
                    val action = MainFragmentDirections.actionMainFragmentToSearchBarFragment()
                    findNavController().navigate(action)
                }
                else -> {
                    Log.d(ShortFilmsFragment.TAG, "onClick: " + v?.id)
                }
            }
        }

    }

}