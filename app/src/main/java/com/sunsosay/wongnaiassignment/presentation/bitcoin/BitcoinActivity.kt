package com.sunsosay.wongnaiassignment.presentation.bitcoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.sunsosay.wongnaiassignment.R
import com.sunsosay.wongnaiassignment.utils.UtilsUI.Companion.hideKeyboard
import kotlinx.android.synthetic.main.activity_bitcoin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Ungkrit Jullawut
 * Wongnai Assignment
 */
class BitcoinActivity : AppCompatActivity() {
    private val viewModel: BitcoinViewModel by viewModel()
    private var mAdapter: BitcoinAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitcoin)
        init()
    }

    private fun init() {
        hideKeyboard()
        initSetAdapterEvent()
        initGetData()
    }

    private fun initSetAdapterEvent() {
        mAdapter = BitcoinAdapter()
        viewModel.dataListCoin.observe(this, Observer { dataList ->
            if (dataList.isNotEmpty()) {
                rvItems.apply {
                    layoutManager = LinearLayoutManager(this@BitcoinActivity)
                    adapter = mAdapter?.apply {
                        if (edtAutocomplete.text.toString().isNotBlank()) {
                            sizeItem = viewModel.sizeItemAdapter
                            fullItems = dataList
                            mAdapter?.filter?.filter(edtAutocomplete.text.toString())
                        } else {
                            sizeItem = viewModel.sizeItemAdapter
                            fullItems = dataList
                            items = dataList
                        }.also {
                            swipeContainer.isRefreshing = false
                        }

                        addOnScrollListener(object : OnScrollListener() {

                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                val visibleItemCount: Int = layoutManager?.childCount!!
                                val totalItemCount: Int = layoutManager?.itemCount!!
                                val pastVisibleItems: Int =
                                    (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()!!

                                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                                    //End of list
                                    swipeContainer.isRefreshing = true
                                    if ((sizeItem + 10) < fullItems?.size!!) {
                                        sizeItem += 10
                                    } else {
                                        sizeItem = fullItems?.size!!
                                    }
                                    notifyDataSetChanged()
                                    swipeContainer.isRefreshing = false
                                }
                                super.onScrolled(recyclerView, dx, dy)
                            }
                        })

                    }

                }
            }
        })

        edtAutocomplete.doOnTextChanged { text, _, _, _ ->
            mAdapter?.filter?.filter(text)
        }

        swipeContainer.setOnRefreshListener {
            getCoin()
        }

    }

    private fun initGetData() {
        getCoin()
    }

    private fun getCoin() {
        viewModel.getCoins()?.observe(this, Observer { dataResponse ->
            viewModel.dataListCoin.value = dataResponse.coins
        })
    }
}
