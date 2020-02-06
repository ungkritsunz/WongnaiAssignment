package com.sunsosay.wongnaiassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sunsosay.wongnaiassignment.Application.Companion.mApplicationContext
import com.sunsosay.wongnaiassignment.R


class UtilsUI {
    companion object {

        fun isNetworkAvailable(): Boolean {
            (mApplicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
                return getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                } ?: false
            }
        }

        fun messageDialogUtil(title: String?, msg: String?) {
            val builder = AlertDialog.Builder(mApplicationContext)
            builder.setTitle(title)
            builder.setMessage(msg)
            builder.setPositiveButton(mApplicationContext.resources.getString(R.string.utils_done)) { dialog, _ -> dialog.dismiss() }
            builder.setCancelable(false)
            builder.create()
            builder.show()
        }

        fun AppCompatActivity.hideKeyboard() {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            } else {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            }
        }

    }
}