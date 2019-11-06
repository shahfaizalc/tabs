package com.merpay.sale.handlers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Handler
import android.util.Log

/**
 * NetworkChangeHandler.
 */

class NetworkChangeHandler {

    /**
     * TAG : class name
     */
    private val TAG = "NetworkStateHandler"

    /**
     * Network change handler
     */
    private val networkChangeHandler = Handler()

    /**
     * Network state Listener
     */
    private var networkStateListener: NetworkStateListener? = null

    private val networkStateChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.action!!, ignoreCase = true)) {
                val state = !intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, java.lang.Boolean.FALSE)
                networkChangeHandler.post {
                    if (networkStateListener != null) {
                        networkStateListener!!.onStateReceived(state)
                    }
                }

            }
        }
    }

    fun registerNetWorkChangeBroadCast(context: Context) {
        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        context.registerReceiver(networkStateChangeReceiver, intentFilter)
    }

    fun setNetworkStateListener(networkStateListener: NetworkStateListener) {
        this.networkStateListener = networkStateListener
    }

    fun unRegisterNetWorkChangeBroadCast(context: Context) {
        try {
            context.unregisterReceiver(networkStateChangeReceiver)
        } catch (ex: Exception) {
            Log.d(TAG, "Exception :" + ex.message)
        }

    }

    interface NetworkStateListener {
        fun onStateReceived(state: Boolean)
    }
}
