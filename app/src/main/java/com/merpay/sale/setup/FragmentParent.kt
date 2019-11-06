package com.merpay.sale.setup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.merpay.sale.R
import com.merpay.sale.view.FragmentHome

import java.io.Serializable

open class FragmentParent : Fragment(), Serializable {

    internal var mFragmentManager: FragmentManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_parent, container, false)
        mFragmentManager = childFragmentManager
        if (mFragmentManager!!.backStackEntryCount < 1) {
            loadFragment(FragmentHome())
            Log.d("TAG", "onCreateView : launch home screen ")
        }
        return view
    }

    fun loadFragment(userFragment: FragmentParent) {
        try {
            if (null != mFragmentManager) {
                val fragmentTransaction = mFragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.fl_reg_fragment_container, userFragment)
                fragmentTransaction.commitAllowingStateLoss()
            }
        } catch (e: IllegalStateException) {
            Log.d(TAG, "loadFragment: Exception occurred :" + e.message)
        }

    }

    companion object {

        private val TAG = "FragmentParent"
    }
}
