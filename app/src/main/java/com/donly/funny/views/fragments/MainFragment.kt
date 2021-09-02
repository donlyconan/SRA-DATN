package com.donly.funny.views.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.donly.funny.R
import com.donly.funny.databinding.FragmentMainBinding
import com.donly.funny.app.adapters.MainSlidesAdapter
import com.donly.funny.widgets.extentions.markSelectedItem
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.min

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var tabIgnoreScollBar: ArrayList<TabLayout.Tab>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.viewPager2.adapter = MainSlidesAdapter(this)
        binding.handler = EventHandler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2, binding.handler as TabLayoutMediator.TabConfigurationStrategy).attach()
        binding.tabLayout.markSelectedItem(R.color.blue_light)
        binding.tabLayout.addOnTabSelectedListener(binding.handler)
        setUpTabItem()
    }

    private fun setUpTabItem() {
        val tabIcons = arrayOf(
            R.drawable.ic_baseline_home,
            R.drawable.ic_baseline_ondemand_video,
            R.drawable.ic_baseline_notifications,
            R.drawable.ic_baseline_menu_options,
        )
        tabIgnoreScollBar = ArrayList()
        for (i in 0 until min(binding.tabLayout.tabCount, tabIcons.size)) {
            val tab = binding.tabLayout.getTabAt(i)
            val tabIcon = tabIcons[i]
            tab?.setIcon(tabIcon)
            if (tab != null && tabIcon != R.drawable.ic_baseline_home) {
                tabIgnoreScollBar.add(tab)
            }
        }
    }


    inner class EventHandler : View.OnClickListener, TabLayout.OnTabSelectedListener,
        TabLayoutMediator.TabConfigurationStrategy {

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.btn_search -> {
                    val action = MainFragmentDirections.actionMainFragmentToSearchBarFragment()
                    findNavController().navigate(action)
                }
            }
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            val container = binding.layoutContainerTop
            if (tabIgnoreScollBar.contains(tab) && container.visibility == View.VISIBLE) {
                container.visibility = View.GONE
            } else if(!tabIgnoreScollBar.contains(tab) && container.visibility != View.VISIBLE) {
                container.visibility = View.VISIBLE
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}

        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {}
    }
}