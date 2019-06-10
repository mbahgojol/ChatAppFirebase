package com.bpdsulteng.mobile.utils

import android.content.Context
import android.content.Intent
import android.view.View
import org.jetbrains.anko.ctx
import java.net.URISyntaxException

/**
 * Created by knalb on 10/06/19.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Mobile Development Engineer
 */

fun tryYukk(a: (Any) -> Unit, b: (Any) -> Unit) {
    try {
        a.invoke(a)
    } catch (e: Exception) {
        b.invoke(e)
        e.printStackTrace()
    }
}

fun tryYukk(a: (Any) -> Unit) {
    try {
        a.invoke(a)
    } catch (e: Exception) {
        e.printStackTrace()
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