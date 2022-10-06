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

class TypingListener(private val changedText: (String) -> Unit) : TextWatcher {

    companion object {
        private const val DEBOUNCE_PERIOD = 1000L
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private var typingJob: Job? = null

    private var isNotTyping = true

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        if (isNotTyping){
//            onTyping(isNotTyping)
//            isNotTyping = false
//        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        typingJob?.cancel()
        s?.let {
            typingJob = coroutineScope.launch {
                delay(DEBOUNCE_PERIOD)
                changedText.invoke(it.toString())
            }
        }
    }
}


fun attatchTabLayoutToViewPager(
    vp: ViewPager2,
    tabLayout: TabLayout,
    onAttatched: (TabLayout.Tab, Int) -> Unit,
) {
    TabLayoutMediator(
        tabLayout,
        vp
    ) { tab, position ->
        onAttatched(tab, position)
    }.attach()
}