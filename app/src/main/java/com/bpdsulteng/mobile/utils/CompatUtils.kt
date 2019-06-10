package com.bpdsulteng.mobile.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import android.view.Window
import android.view.WindowManager
import org.jetbrains.anko.ctx
import java.net.URISyntaxException

/**
 * Created by knalb on 12/05/19.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
class CompatUtils {
    companion object {
        fun showDialog(context: Context, layoutId: Int, d: (Dialog) -> Unit) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(layoutId)
            dialog.setCancelable(true)
            d.invoke(dialog)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.show()
            dialog.window!!.attributes = lp
        }

        fun dp2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        fun changeTintColorDrawable(v: View, color: Int) {
            var buttonDrawable = v.background
            buttonDrawable = DrawableCompat.wrap(buttonDrawable)
            DrawableCompat.setTint(buttonDrawable, color)
            v.background = buttonDrawable
        }

        fun changeBgColor(v: View, colorStateList: ColorStateList) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                v.backgroundTintList = colorStateList
            }
        }
    }
}