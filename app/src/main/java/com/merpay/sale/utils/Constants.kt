package com.merpay.sale.utils

import com.merpay.sale.view.SaleAllFragment
import com.merpay.sale.view.SaleMenFragment
import com.merpay.sale.view.SaleWomenFragment

object Constants {

    //List of fragments to add on TABS in order
    val SALE_FRAGMENTS = arrayOf(SaleMenFragment(), SaleAllFragment(), SaleWomenFragment())

    //key
    val SOLDOUT = "sold_out"

    /**
     * BASE URL
     */
    val BASE_URL = "https://s3-ap-northeast-1.amazonaws.com/m-et/Android/json/"
}
