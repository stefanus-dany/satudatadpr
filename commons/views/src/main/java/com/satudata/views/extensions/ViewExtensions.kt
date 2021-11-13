package com.satudata.views.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.satudata.views.SafeClickListener


fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun AppCompatButton.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Button.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun AppCompatTextView.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun TextView.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun AppCompatTextView.setTextViewDrawableColor(color: Int) {
    for (drawable in compoundDrawablesRelative) {
        if (drawable != null) {
            drawable.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(context, color),
                    PorterDuff.Mode.SRC_IN
                )
        }
    }
}

fun AppCompatButton.setDrawableColor(color: Int) {
    for (drawable in compoundDrawablesRelative) {
        if (drawable != null) {
            drawable.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(context, color),
                    PorterDuff.Mode.MULTIPLY
                )
        }
    }
}


fun String?.underline(): SpannableString {
    val text = SpannableString(this)
    if (this == null) {
        return text
    }
    text.setSpan(
        UnderlineSpan(),
        0, // start
        this.length, // end
        0 // flags
    )
    return text
}

fun View.setMargin(
    leftMargin: Int? = null,
    topMargin: Int? = null,
    rightMargin: Int? = null,
    bottomMargin: Int? = null
) {
    val params = layoutParams as ViewGroup.MarginLayoutParams

    params.setMargins(
        leftMargin ?: params.leftMargin,
        topMargin ?: params.topMargin,
        rightMargin ?: params.rightMargin,
        bottomMargin ?: params.bottomMargin
    )

    layoutParams = params
}

fun TextInputLayout.addAsteriskSign(@ColorInt idColor: Int) {
    val colored = "*"
    val builder = SpannableStringBuilder()
    builder.append(this.hint)
    val start = builder.length
    builder.append(colored)
    val end = builder.length

    builder.setSpan(
        ForegroundColorSpan(idColor), start, end,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.hint = builder
}
