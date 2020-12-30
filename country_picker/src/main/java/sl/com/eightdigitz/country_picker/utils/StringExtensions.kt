package sl.com.eightdigitz.country_picker.utils

import android.util.Log

fun String.toCapitalizeFirstLetter(): String {
    val words = this.split(" ").toMutableList()

    var output = ""

    for (word in words) {
        output += word.capitalize() + " "
    }
    Log.e("****", output)

    return output.trim()
}