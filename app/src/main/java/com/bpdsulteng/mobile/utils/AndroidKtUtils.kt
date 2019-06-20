package com.bpdsulteng.mobile.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.View.*
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import org.jetbrains.anko.act
import org.jetbrains.anko.ctx
import java.net.URISyntaxException

/**
 * Created by knalb on 10/06/19.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Mobile Development Engineer
 */

fun tryYukk(a: () -> Unit, b: (Any) -> Unit) {
    try {
        a()
    } catch (e: Exception) {
        b(e)
        e.printStackTrace()
    }
}

fun tryYukk(a: () -> Unit) {
    try {
        a()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

infix fun View.action(any: () -> Unit) {
    setOnClickListener {
        any()
    }
}

infix fun View.click(any: (View) -> Unit) {
    setOnClickListener {
        any(it)
    }
}

infix fun View.goTo(cls: Class<*>) {
    var i = Intent(rootView.context, cls)
    setOnClickListener { rootView.context.startActivity(i) }
}

infix fun Context.goTo(url: String) {
    var i = Intent.getIntent(url)
    startActivity(i)
}

infix fun Context.goTo(cls: Class<*>) {
    startActivity(Intent(ctx, cls))
}


infix fun View.goTo(url: String) {
    try {
        var i = Intent.getIntent(url)
        setOnClickListener { rootView.context.startActivity(i) }
    } catch (e: URISyntaxException) {
        e.printStackTrace()
    }
}

internal fun View.gone() {
    try {
        this.visibility = GONE
    } catch (e: URISyntaxException) {
        e.printStackTrace()
    }
}

internal fun View.visible() {
    try {
        this.visibility = VISIBLE
    } catch (e: URISyntaxException) {
        e.printStackTrace()
    }
}

internal fun View.invisible() {
    try {
        this.visibility = INVISIBLE
    } catch (e: URISyntaxException) {
        e.printStackTrace()
    }
}

internal fun Activity.screenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    act.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

internal fun Activity.screenHeight(): Int {
    val displayMetrics = DisplayMetrics()
    act.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}

infix fun Context.dpToPx(dp: Int): Int {
    val r = ctx.resources
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r?.displayMetrics))
}

infix fun Context.dip2px(dpValue: Float): Int {
    val scale = ctx.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

infix fun Context.px2dip(pxValue: Float): Int {
    val scale = this.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

internal fun Context.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

internal fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/*infix fun Context.requestOption(radius: Int): RequestOptions {
    return RequestOptions().transforms(CenterCrop(), RoundedCorners(radius))
}*/

internal fun Window.transparentStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        this.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}

internal fun Window.hideStatusBar() {
    this.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

internal fun Window.showStatusBar() {
    this.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

internal fun Activity.mintaPermissions(@NonNull permissions: Array<String>, requestCode: Int) {
    try {
        ActivityCompat.requestPermissions(act, permissions, requestCode)
    } catch (e: URISyntaxException) {
        e.printStackTrace()
    }
}

internal fun Activity.punyaPermissions(@NonNull permissions: String): Boolean {
    try {
        return ActivityCompat.checkSelfPermission(act, permissions) == PackageManager.PERMISSION_GRANTED
    } catch (e: URISyntaxException) {
        e.printStackTrace()
        return false
    }
}

internal fun FragmentManager.replace(id: Int, fragment: Fragment, tag: String): FragmentTransaction =
        beginTransaction().replace(id, fragment, tag)

internal fun FragmentManager.replace(id: Int, fragment: Fragment) = beginTransaction().replace(id, fragment)

internal fun FragmentManager.add(id: Int, fragment: Fragment) = beginTransaction().replace(id, fragment)