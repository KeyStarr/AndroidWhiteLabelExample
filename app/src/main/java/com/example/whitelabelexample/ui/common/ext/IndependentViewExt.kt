package com.example.whitelabelexample.ui.common.ext

import android.content.Context
import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.ViewTreeObserver
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.BoolRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    return try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
        // pass
        false
    }
}

fun EditText.onEndCallListener(action: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == KeyEvent.KEYCODE_ENDCALL) {
            action.invoke()
        }
        false
    }
}

@Suppress("DEPRECATION")
fun FragmentActivity.setupStatusBarColor(
    @ColorRes statusBarColorRes: Int,
    isLightStatusBar: Boolean = false
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val systemUiAppearance = if (isLightStatusBar) APPEARANCE_LIGHT_STATUS_BARS else 0
            window.insetsController?.setSystemBarsAppearance(
                systemUiAppearance,
                APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            val systemUiVisibilityFlags = if (isLightStatusBar) {
                window.decorView.systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility and SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            window.decorView.systemUiVisibility = systemUiVisibilityFlags
        }
        window.statusBarColor = ContextCompat.getColor(this, statusBarColorRes)
    }
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.onSizeCalculated(callback: (width: Int, height: Int) -> Unit) {
    val view = this
    viewTreeObserver.addOnGlobalLayoutListener(
        object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (view.width > 0) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    callback(view.width, view.height)
                }
            }
        }
    )
}

fun Context.getBool(@BoolRes boolResId: Int) = resources.getBoolean(boolResId)
