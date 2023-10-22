package com.hilton.jobsearch.ui

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.hilton.jobsearch.R

/**
 * for data binding properties update
 */
object BindingAdapter {

    @BindingAdapter("showOnSuccess")
    @JvmStatic fun showOnSuccess(view: View, uiState: UiState?) {
        if (uiState is UiState.Success) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("showOnDefaultAndError")
    @JvmStatic fun showOnDefaultAndError(view: View, uiState: UiState?) {
        if (uiState is UiState.Default || uiState is UiState.Error) {
            view.visibility = View.VISIBLE
            if (uiState is UiState.Error && view is Button) {
                view.text = view.resources.getText(R.string.retry)
            }
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("showOnLoading")
    fun showOnLoading(progressBar: ProgressBar, uiState: UiState?) {
        if (uiState is UiState.Loading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }


    @BindingAdapter("showOnError")
    @JvmStatic fun showError(textView: TextView, uiState: UiState?) {
        if (uiState is UiState.Error) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }

        textView.text = (uiState as? UiState.Error)?.message
    }
}