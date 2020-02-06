package com.sunsosay.wongnaiassignment.presentation.bitcoin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunsosay.wongnaiassignment.Application
import com.sunsosay.wongnaiassignment.R
import com.sunsosay.wongnaiassignment.model.Coin
import com.sunsosay.wongnaiassignment.utils.Constants.Companion.VIEWTYPE_EMPTY
import com.sunsosay.wongnaiassignment.utils.Constants.Companion.VIEWTYPE_NORMAL
import com.sunsosay.wongnaiassignment.utils.Constants.Companion.VIEWTYPE_UNIQUE
import kotlinx.android.synthetic.main.item_bitcoin.view.*
import kotlinx.android.synthetic.main.item_unique_bitcoin.view.*
import kotlin.properties.Delegates


class BitcoinAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var items by Delegates.observable(mutableListOf<Coin>()) { _, _, _ -> notifyDataSetChanged() }
    var fullItems: MutableList<Coin>? = null
    var sizeItem: Int = 0
    //    override fun getItemCount() = items?.let { items!!.size } ?: 0
    override fun getItemCount() = if (sizeItem > items.size) items.size else sizeItem

    override fun getItemViewType(position: Int): Int {
        return when {
            items.isNullOrEmpty() -> {
                VIEWTYPE_EMPTY
            }
            ((position + 1) % 5) == 0 -> {
                VIEWTYPE_UNIQUE
            }
            else -> {
                VIEWTYPE_NORMAL
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEWTYPE_NORMAL -> {
                ViewHolderNormal(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_bitcoin,
                        parent,
                        false
                    )
                )
            }
            VIEWTYPE_UNIQUE -> {
                ViewHolderUnique(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_unique_bitcoin,
                        parent,
                        false
                    )
                )
            }
            else -> {
                EmptyViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_empty,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].apply {
            when (getItemViewType(position)) {
                VIEWTYPE_NORMAL -> (holder as ViewHolderNormal).bindNormal(this)
                VIEWTYPE_UNIQUE -> (holder as ViewHolderUnique).bindUnique(this)
            }
        }
    }


    class ViewHolderNormal(view: View) : RecyclerView.ViewHolder(view) {
        private val headerItem: TextView = view.tvHeaderItem
        private val detailItem: TextView = view.tvDetailItem
        private val iconHeaderItem: ImageView = view.ivIconHeaderItem
        fun bindNormal(dataObj: Coin) {
            dataObj.apply {
                headerItem.text = name ?: "-"
                detailItem.text = description ?: "-"
                Glide.with(Application.mApplicationContext).load(iconUrl).dontTransform()
                    .into(iconHeaderItem)
            }
        }
    }

    class ViewHolderUnique(view: View) : RecyclerView.ViewHolder(view) {
        private val headerItemUnique: TextView = view.tvHeaderItemUnique
        private val iconHeaderItemUnique: ImageView = view.ivIconHeaderItemUnique
        fun bindUnique(dataObj: Coin) {
            dataObj.apply {
                headerItemUnique.text = name ?: "-"
                Glide.with(Application.mApplicationContext).load(iconUrl).dontTransform()
                    .into(iconHeaderItemUnique)
            }
        }

    }

    class EmptyViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSeq: CharSequence?): FilterResults {
                var listCoin: MutableList<Coin> = ArrayList()
                val strChange = charSeq.toString()
                if (strChange.isBlank()) {
                    if (fullItems != null)
                        listCoin = fullItems!!
                } else {
                    fullItems?.forEach { data ->
                        if (filterTextContains(strChange, data))
                            listCoin.add(data)
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = listCoin
                return filterResult
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                items = filterResults.values as MutableList<Coin>
            }
        }
    }

    // Search box filter by prefix, symbols, slugs or IDs
    @SuppressLint("DefaultLocale")
    private fun filterTextContains(textChange: String, coin: Coin): Boolean {
        return when (true) {
            coin.symbol?.toLowerCase()?.contains(textChange.toLowerCase()) -> true
            coin.slug?.toLowerCase()?.contains(textChange.toLowerCase()) -> true
            coin.id?.toString()?.toLowerCase()?.contains(textChange.toLowerCase()) -> true
            else -> false
        }
    }

}




