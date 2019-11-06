package com.merpay.sale.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.merpay.sale.R
import com.merpay.sale.adapters.ViewPagerAdapter
import com.merpay.sale.databinding.ActivityHomeBinding
import com.merpay.sale.databinding.FragmentDetailsBinding
import com.merpay.sale.setup.FragmentParent
import com.merpay.sale.utils.Constants
import com.merpay.sale.viewmodel.HomeViewModel

class FragmentHome : FragmentParent() {

    internal var binding: ActivityHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return bindView(inflater, container)
    }

    private fun bindView(inflater: LayoutInflater, container: ViewGroup?): View {
        if (binding == null) {
            binding = ActivityHomeBinding.inflate(inflater, container, false)
            val areaViewModel = HomeViewModel()
            binding!!.homeData = areaViewModel
            binding!!.homeData!!.setPagerAdapter(adapter)
        }
        return binding!!.root
    }

    val adapter: ViewPagerAdapter
        get() {
            val adapter = ViewPagerAdapter(activity!!.supportFragmentManager)
            val number = resources.getStringArray(R.array.tabs)
            for (i in number.indices) {
                adapter.addFrag(Constants.SALE_FRAGMENTS[i] as Fragment, number[i])
            }
            return adapter
        }
}
