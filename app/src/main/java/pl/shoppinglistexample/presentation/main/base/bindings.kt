package pl.shoppinglistexample.presentation.main.base

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("android:errorText")
fun setError(view: TextInputLayout, errorTxt: String?) {
    view.error = errorTxt
}

@BindingAdapter("android:visibleOrGone")
fun setVisibleOrGone(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:setEnabled")
fun setEnabled(view: View, isEnabled: Boolean) {
    view.isEnabled = isEnabled
}