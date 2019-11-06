package com.merpay.sale.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.merpay.sale.R
import com.merpay.sale.databinding.FragmentDetailsBinding
import com.merpay.sale.handlers.NetworkChangeHandler
import com.merpay.sale.model.Sale
import com.merpay.sale.setup.FragmentParent
import com.merpay.sale.utils.RequestType
import com.merpay.sale.viewmodel.SalesViewModel

class SaleAllFragment : FragmentParent(), NetworkChangeHandler.NetworkStateListener {

    /**
     * Binding
     */
    internal var binding: FragmentDetailsBinding? = null

    /**
     * Network change listener
     */
    lateinit var networkChangeHandler: NetworkChangeHandler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null) {
            binding = FragmentDetailsBinding.inflate(inflater, container, false)
            val areaViewModel = ViewModelProviders.of(this).get(SalesViewModel::class.java)

            setBindingAttributes(areaViewModel)
        }
        return binding!!.root
    }

    private fun setBindingAttributes(areaViewModel: SalesViewModel) {
        binding!!.mainData = areaViewModel
        binding!!.included.mainData = areaViewModel
        doRequest()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkChangeHandler = NetworkChangeHandler()
        binding!!.included.retry.setOnClickListener { view1 -> doRequest() }
    }


    private fun doRequest() {
        binding!!.mainData!!.progressBarVisible = View.VISIBLE
        binding!!.mainData!!.getAllSalesList(RequestType.ALL).observe(this,object :Observer<List<Sale>>{
            override fun onChanged(t: List<Sale>?) {
                binding!!.mainData!!.saleItemsAdapter!!.setSalesItems(t!!)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        networkChangeHandler.unRegisterNetWorkChangeBroadCast(this.context!!)
    }

    override fun onResume() {
        super.onResume()
        networkChangeHandler.setNetworkStateListener(this)
        networkChangeHandler.registerNetWorkChangeBroadCast(this.context!!)
    }

    override fun onStateReceived(state: Boolean) {
        binding!!.included.retry.isEnabled = state
        if (!state) {
            Toast.makeText(this.context, R.string.network_ErrorMsg, Toast.LENGTH_LONG).show()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        val columns = context!!.getResources().getInteger(R.integer.gallery_columns);
        binding!!.mainData!!.linearLayoutManager!!.setSpanCount(columns);

    }
}
