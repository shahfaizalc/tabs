package com.merpay.sale.adapters

import android.databinding.BindingAdapter
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.ImageView
import com.merpay.sale.R
import com.merpay.sale.viewmodel.SalesViewModel
import com.squareup.picasso.Picasso


/**
 * To setup view pager
 * @param tabLayout
 * @param viewPager
 */
@BindingAdapter("setUpWithViewpager")
fun setUpWithViewpager(tabLayout: TabLayout, viewPager: ViewPager) {
    viewPager.addOnAdapterChangeListener { viewPager1, oldAdapter, newAdapter ->
        Log.d("TAG", "onAdapterChanged")
        if (!(oldAdapter == null && newAdapter == null)) {
            tabLayout.setupWithViewPager(viewPager1)
        }
    }
}

/**
 *
 * @param view Image view
 * @param imageUrl image url
 */
@BindingAdapter("app:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.get().load(imageUrl).into(view)
}

/**
 * To show the list of sales items in recyclerview
 * @param salesViewModel : View model of sales items lists
 * @param recyclerView       : List of sales items recycler view
 */
@BindingAdapter("app:salesListAdapter")
fun adapter(recyclerView: RecyclerView, salesViewModel: SalesViewModel) {
     val columns = recyclerView.context.getResources().getInteger(R.integer.gallery_columns);

    val linearLayoutManager = StaggeredGridLayoutManager(columns,  GridLayoutManager.VERTICAL)
    recyclerView.layoutManager = linearLayoutManager
    val listAdapter = SaleItemsAdapter(salesViewModel)
    recyclerView.adapter = listAdapter
    salesViewModel.saleItemsAdapter = listAdapter
    salesViewModel.linearLayoutManager= linearLayoutManager
}


