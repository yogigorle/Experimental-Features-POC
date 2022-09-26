package com.example.experimentalfeaturespoc

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

fun <T> mutableListWithCapacity(capacity: Int): MutableList<T> = ArrayList(capacity)

fun Context.checkPermission(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.checkPermissions(permissions: Array<String>) =
    ActivityCompat.checkSelfPermission(this,
        permissions[0]) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
        this,
        permissions[1]) == PackageManager.PERMISSION_GRANTED

fun Context.showToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.showSnackbar(
    msg: String,
    length: Int = Snackbar.LENGTH_SHORT,
    actionMessage: CharSequence? = null,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    } else {
        snackbar.show()
    }
}
fun dpToPx(dp: Int): Int {
    return ((dp * Resources.getSystem().displayMetrics.density).toInt());
}

fun pxToDp(px: Int, context: Context): Int {
    return ((px / Resources.getSystem().displayMetrics.density).toInt());
}