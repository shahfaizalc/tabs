package com.merpay.sale.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v4.view.PagerAdapter
import android.util.Log
import com.merpay.sale.BR
import com.merpay.sale.adapters.ViewPagerAdapter

class HomeViewModel : BaseObservable() {

    /**
     * TAG
     */
    private val TAG = "HomeViewModel"

    /**
     * View Pager Adapter
     */
    private var adapter: ViewPagerAdapter? = null

    val pagerAdapter: PagerAdapter?
        @Bindable
        get() = adapter

    fun setPagerAdapter(adapter: ViewPagerAdapter) {
        Log.d(TAG, "setPagerAdapter")
        this.adapter = adapter
        notifyPropertyChanged(BR.pagerAdapter)
    }
}
