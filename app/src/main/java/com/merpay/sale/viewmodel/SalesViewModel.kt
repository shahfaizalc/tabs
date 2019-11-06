package com.merpay.sale.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.Bindable
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import com.merpay.sale.BR
import com.merpay.sale.adapters.SaleItemsAdapter
import com.merpay.sale.listeners.ResultListener
import com.merpay.sale.model.Sale
import com.merpay.sale.repository.SalesItemsRepository
import com.merpay.sale.utils.RequestType
import com.merpay.sale.utils.ViewModelCallback


class SalesViewModel : ViewModelCallback(), ResultListener {

    /**
     * TAG
     */
    private val TAG = "SalesViewModel"

    /**
     * SalesItems repository
     */
    private val salesItemsRepository: SalesItemsRepository

    /**
     * SaleItemsAdapter
     */
    var saleItemsAdapter: SaleItemsAdapter? = null
    var linearLayoutManager: StaggeredGridLayoutManager? = null


    init {
        salesItemsRepository = SalesItemsRepository()
    }

    /**
     * Progress bar visibility
     */
    @get:Bindable
    var progressBarVisible = View.VISIBLE
        set(progressBarVisible) {
            field = progressBarVisible
            notifyPropertyChanged(BR.progressBarVisible)
        }

    /**
     * UI state on error
     */
    @get:Bindable
    var onError = View.GONE
        set(onError) {
            field = onError
            notifyPropertyChanged(com.merpay.sale.BR.onError)
        }

    fun getAllSalesList(requestType: RequestType): MutableLiveData<List<Sale>>
            = salesItemsRepository.getMutableLiveData(this,  requestType)

    override fun onError(err: String) {
        Log.d(TAG, "onError: onError")
        progressBarVisible = View.GONE
        onError = View.VISIBLE
    }

    override fun onSuccess() {
        Log.d(TAG, "onSuccess: onSuccess")
        progressBarVisible = View.GONE
        onError = View.GONE
    }
}
