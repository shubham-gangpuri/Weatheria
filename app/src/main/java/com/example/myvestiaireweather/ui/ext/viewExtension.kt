package com.example.myvestiaireweather.ui.ext

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.example.myvestiaireweather.domain.state.UIComponent
import java.text.SimpleDateFormat
import java.util.*

/** @param message:Boolean
 * @param onClick:Boolean
 * extention function of material Dialog to showError Log
 *
 *
 */
fun Context.showError(uiComponent: UIComponent, onClick:()->Unit){
    if (uiComponent is UIComponent.Dialog){
        MaterialDialog(this).show {
            title(text = uiComponent.title)
            message(text = uiComponent.description)
            negativeButton(text = "close") {
                dismiss()
                onClick()
            }
        }
    }
}



fun Long.formatUnixTime(pattern:String): String {
    val date = Date(this * 1000L)
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(date)
}
