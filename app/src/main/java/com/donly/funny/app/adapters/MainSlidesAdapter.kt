package com.donly.funny.app.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.donly.funny.views.fragments.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainSlidesAdapter @Inject constructor(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private lateinit var mListFragment: ArrayList<Fragment>

    init {
        mListFragment = arrayListOf(
            FirstFragment(),
            ShortFilmsFragment(),
            NotificationFragment(),
            FourthFragment()
        )
    }


    override fun createFragment(position: Int): Fragment {
        return mListFragment[position]
    }

    override fun getItemCount(): Int {
        return mListFragment.size
    }
}