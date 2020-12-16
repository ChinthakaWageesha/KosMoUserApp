package sl.com.eightdigitz.presentation.extensions

import com.google.gson.Gson
import com.google.gson.GsonBuilder

inline fun <reified T : Any> Any.mapTo(): T =
    GsonBuilder().create().run {
        toJson(this@mapTo).let { fromJson(it, T::class.java) }
    }

inline fun <reified T : Any> String.jsonStringMapTo(): T =
    Gson().fromJson(this@jsonStringMapTo, T::class.java)

fun Any.toJsonString(): String = Gson().toJson(this@toJsonString)
