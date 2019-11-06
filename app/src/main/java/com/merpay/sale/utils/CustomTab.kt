package com.merpay.sale.utils

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.merpay.sale.R

class CustomTab(internal var context: Context, attrs: AttributeSet) : TabLayout(context, attrs) {

    private val TAG = "CustomTab"

    override fun setupWithViewPager(viewPager: ViewPager?) {
        super.setupWithViewPager(viewPager)
        Log.i(TAG, "setupWithViewPager")
        if (viewPager!!.adapter == null) {
            return
        }
        val tabs = context.resources.getStringArray(R.array.tabs)
        for (tabsCount in tabs.indices) {
            addTabs(tabs[tabsCount], tabsCount)
        }
    }

    private fun addTabs(tabName: String, i: Int) {
        val tabOne = LayoutInflater.from(getContext()).inflate(R.layout.custom_tab, null) as LinearLayout
        (tabOne.findViewById<View>(R.id.text_title) as TextView).text = tabName
        this.getTabAt(i)!!.customView = tabOne
    }
}
