package com.example.whitelabelexample.common.ext

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.*
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.BoolRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.whitelabelexample.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.net.URI

// TODO: remove useless

fun <T> LiveData<T>.observe(owner: LifecycleOwner, block: (T) -> Unit) {
    observe(owner, Observer { block(it) })
}

fun Context.getDrawableURI(name: String): URI {
    val resId = getDrawableResId(name)
    val drawableUri = Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://"
                + resources.getResourcePackageName(resId)
                + '/'.toString() + resources.getResourceTypeName(resId)
                + '/'.toString() + resources.getResourceEntryName(resId)
    )
    return URI.create(drawableUri.toString())
}

fun Context.getDrawableResId(name: String) = resources.getIdentifier(name, "drawable", packageName)

/**
 * Extension method to inflate layout for ViewGroup.
 */
fun <T : View> ViewGroup.inflate(layoutRes: Int, attachToRoot: Boolean = false): T {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot) as T
}

fun Context.showErrorAlertDialog(msgResId: Int) {
    val msg = getString(msgResId)
    showErrorAlertDialog(msg)
}

fun Context.showErrorAlertDialog(msg: String) {
    MaterialAlertDialogBuilder(this, R.style.WhiteDialogStyle)
        .setMessage(msg)
        .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        .show()
}

fun Context.showStandardAlertDialog(title: String, msg: String) {
    MaterialAlertDialogBuilder(this, R.style.WhiteDialogStyle)
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        .show()
}

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun EditText.onEndCallListener(action: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == KeyEvent.KEYCODE_ENDCALL) {
            action.invoke()
        }
        false
    }
}

fun FragmentActivity.setupStatusBarColor(
    @ColorRes statusBarColorRes: Int,
    lightStatusBar: Boolean = false
) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        return
    } else {
        val decorView = window.decorView

        window?.statusBarColor = ContextCompat.getColor(this, statusBarColorRes)

        val flags = if (lightStatusBar) {
            decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decorView.systemUiVisibility xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // use XOR here for remove LIGHT_STATUS_BAR from flags
        }
        decorView.systemUiVisibility = flags
    }
}

fun TextView.setHtmlAsText(html: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun Context.getDimen(@DimenRes dimenResId: Int) = resources.getDimension(dimenResId)

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

fun Context.inflate(@LayoutRes layoutId: Int, root: ViewGroup, attachToRoot: Boolean): View =
    LayoutInflater.from(this).inflate(layoutId, root, attachToRoot)

fun Context.color(@ColorRes colorResId: Int) = ContextCompat.getColor(this, colorResId)

fun Context.getBool(@BoolRes boolResId: Int) = resources.getBoolean(boolResId)

suspend fun <T> withIO(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.IO, block)

fun <T> CoroutineScope.asyncIO(block: suspend CoroutineScope.() -> T) =
    async(Dispatchers.IO) { block() }


fun <T> List<T>.has(isValid: (T) -> Boolean) = find { isValid(it) } != null

fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}

fun View.setVisibleWithFadeAnimation(visible: Boolean, durationMs: Long = 300) {
    if (visible) animateFadeInVisibility(durationMs) else animateFadeOutVisibility(durationMs)
}

fun View.animateFadeInVisibility(durationMs: Long) {
    setVisible(true)
    animateFadeIn(durationMs)
}

fun View.animateFadeOutVisibility(durationMs: Long) {
    animateFadeOut(durationMs) {
        setVisible(false)
    }
}

fun View.animateFadeIn(durationMs: Long, onEndCallback: (() -> Unit)? = null) {
    clearAnimation()
    val animation = animate()
        .alpha(1F)
        .setDuration(durationMs)
        .setInterpolator(DecelerateInterpolator())
    onEndCallback?.let {
        animation.withEndAction(it)
    }
    animation.start()
}

fun View.animateFadeOut(durationMs: Long, onEndCallback: (() -> Unit)? = null) {
    clearAnimation()
    val animation = animate()
        .alpha(0F)
        .setDuration(durationMs)
        .setInterpolator(DecelerateInterpolator())
    onEndCallback?.let {
        animation.withEndAction(it)
    }
    animation.start()
}

fun RecyclerView.init(
    adapter: RecyclerView.Adapter<*>,
    manager: RecyclerView.LayoutManager = LinearLayoutManager(context)
) {
    this.adapter = adapter
    this.layoutManager = manager
}

fun ViewPager.addOnPageChangeCallback(callback: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

        override fun onPageSelected(position: Int) {
            callback(position)
        }
    })
}
