package com.example.experimentalfeaturespoc

import java.time.DayOfWeek
import java.time.temporal.WeekFields
import java.util.*
import kotlin.collections.ArrayList

fun <T> mutableListWithCapacity(capacity: Int): MutableList<T> = ArrayList(capacity)

fun daysOfWeekFromLocale(): Array<DayOfWeek> {
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
    var daysOfWeek = DayOfWeek.values()
    // Order `daysOfWeek` array so that firstDayOfWeek is at index 0.
    // Only necessary if firstDayOfWeek != DayOfWeek.MONDAY which has ordinal 0.
    if (firstDayOfWeek != DayOfWeek.MONDAY) {
        val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
        val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
        daysOfWeek = rhs + lhs
    }
    return daysOfWeek
}


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