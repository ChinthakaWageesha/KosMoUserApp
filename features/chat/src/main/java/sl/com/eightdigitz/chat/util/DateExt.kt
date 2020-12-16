package sl.com.eightdigitz.chat.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


const val YYYY_MM_DD = "yyyy-MM-dd"
const val YYYY_MM_DD_hh_mm_ss = "yyyy-MM-dd hh:mm:ss"
const val DD_MM_YYYY = "dd/MM/yyyy"
const val DD_MMM = "dd MMM"
const val DD = "dd"
const val HH_MM = "h:mm a"
const val DD_MMMM_EEEE = "dd'%s' MMMM, EEEE"
const val DD_MMM_EEEE = "dd MMM, EEEE"
const val DD_MMM_YYYY = "dd MMM, yyyy"
const val DD_MMM_YYYY_H_MM_AP = "dd/MMM/yyyy h:mm a"


/**
 * standard date format String to Date
 */
fun String.toDate(): Date? {
    val simpleDateFormat = SimpleDateFormat(YYYY_MM_DD, Locale.US)
    return try {
        simpleDateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

/**
 * standard date and time format String to Date
 */
fun String.toDateWithTime(): Date? {
    val simpleDateFormat = SimpleDateFormat(YYYY_MM_DD_hh_mm_ss, Locale.US)
    return try {
        simpleDateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

/**
 * Date to custom format String
 */
fun Date.toCustomDate(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale.US)
    return simpleDateFormat.format(this)
}

/**
 * milis to Date
 */
fun Long.toDate(): Date {
    val date = Date()
    date.time = this
    return date
}

/**
 * standard date format String to custom String
 */
fun String.toCustomDate(format: String): String {
    return this.toDate()?.let {
        it.toCustomDate(format)
    } ?: ""
}

/**
 * num of days between two days
 */
fun Calendar.calculateDays(calendarSec: Calendar): Long {
    val diff = this.timeInMillis - calendarSec.timeInMillis
    return TimeUnit.MILLISECONDS.toDays(diff)
}

/**
 * different with current time milis
 */
fun Long.differentFromCurrent(): Long {
    val dif = System.currentTimeMillis().minus(this)
    return dif
}

/**
 * standard date format string to date with suffix
 */
fun String.toCustomDateWithSuffix(): String {
    val suffix =
        getDayOfMonthSuffix(this.toCustomDate(DD).toInt())
    val customDate = this.toCustomDate(DD_MMMM_EEEE)
    return String.format(customDate, suffix)
}

/**
 * get date suffix
 */
fun getDayOfMonthSuffix(n: Int): String {
    //checkArgument(n >= 1 && n <= 31, "illegal day of month: $n")
    if (n in 11..13) {
        return "th"
    }
    return when (n % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }
}

/**
 * get date title
 */
fun Long.getDate(): String {
    val calendat = Calendar.getInstance()
    calendat.timeInMillis = this
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }
    val yetdyCalendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        add(Calendar.DATE, -1)
    }

    return if (calendat.after(today)) {
        "Today"
    } else if (calendat.after(yetdyCalendar)) {
        "Yesterday"
    } else {
        val date = Date()
        date.time = this
        date.toCustomDate(DD_MMM_YYYY)
    }
}

/**
 * get time title
 */
fun Long.getTime(): String {
    val dif = this.differentFromCurrent()
    val min = TimeUnit.MILLISECONDS.toMinutes(dif)
    val hours = TimeUnit.MINUTES.toHours(min)
    val days = TimeUnit.HOURS.toDays(hours)
    return if (min.toInt() == 0) {
        "Just now"
    } else if (min < 60) {
        "$min min ago"
    } else {
        if (hours < 24) {
            "${hours.toInt()}h ago"
        } else {
            if (days < 2) {
                "Yesterday"
            } else {
                val date = Date()
                date.time = this
                date.toCustomDate(DD_MMM_YYYY)
            }
        }
    }
}

fun Long.milisToHours(): String {
    var secound = this.div(1000)
    var minite = 0
    var hours = 0

    if (secound >= 60) {
        minite = secound.div(60).toInt()
        secound %= 60
    }
    if (minite >= 60) {
        hours = minite.div(60).toInt()
        minite %= 60
    }

    return "${String.format("%02d", hours)}:${String.format("%02d", minite)}:${String.format(
        "%02d",
        secound
    )}"
}

fun Long.milisToMinute(): String {
    var secound = this.div(1000)
    var minite = 0

    if (secound >= 60) {
        minite = secound.div(60).toInt()
        secound %= 60
    }
    return "${String.format("%02d", minite)}:${String.format("%02d", secound)}"
}

fun Long.milisToSecound(): Long {
    val secound = this.div(1000)

    return secound
}