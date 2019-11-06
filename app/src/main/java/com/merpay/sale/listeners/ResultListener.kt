package com.merpay.sale.listeners

/**
 * interface to handle response callback
 */
interface ResultListener {
    fun onError(err: String)
    fun onSuccess()
}