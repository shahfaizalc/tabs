package com.merpay.sale.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.merpay.sale.utils.Constants.BASE_URL

/**
 * Retrofit Client Instance
 */
object RetrofitClientInstance {

    /**
     * Retrofit
     */
    private var retrofit: Retrofit? = null

    /**
     * Function to initialize retrofit instance
     * @return retrofit
     */
    fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}