package com.donly.funny.widgets.extentions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout

/**
 * Mask that this item is selected
 */
fun TabLayout.markSelectedItem(@ColorRes colorSelected: Int) {
    val listener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            for (i in 0 until tabCount) {
               val tab =  getTabAt(i)
                if(i == selectedTabPosition) {
                    tab?.icon?.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context,
                        colorSelected), PorterDuff.Mode.SRC_ATOP)
                } else {
                    tab?.icon?.colorFilter = null
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    }
    addOnTabSelectedListener(listener)
}


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Context.showToast(resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}