package com.hilton.jobsearch.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * for data binding properties update
 */
object BindingAdapter {

    @BindingAdapter("app:hideOnLoading")
    @JvmStatic fun hideOnLoading(view: View, uiState: UiState?) {
        if (uiState is UiState.Loading) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("app:showOnLoading")
    fun showOnLoading(progressBar: ProgressBar, uiState: UiState?) {
        if (uiState is UiState.Loading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }


    @BindingAdapter("app:showOnError")
    @JvmStatic fun showError(textView: TextView, uiState: UiState?) {
        if (uiState is UiState.Error) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }

        textView.text = (uiState as? UiState.Error)?.message
    }
}