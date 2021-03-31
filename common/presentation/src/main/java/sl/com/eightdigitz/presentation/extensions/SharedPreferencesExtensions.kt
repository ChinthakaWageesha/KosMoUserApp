package sl.com.eightdigitz.presentation.extensions

import android.content.SharedPreferences
import sl.com.eightdigitz.presentation.Constant

inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}

fun SharedPreferences.setValue(key: String, value: Any?) {
    when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

inline fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String) as T?
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}

fun SharedPreferences.cleaAll() {
    edit { it.clear().apply() }
}

fun SharedPreferences.getUserString(): String? = getString(Constant.PREF_USER, "")
//fun SharedPreferences.getUserString() = "{\"authReference\":\"sms|5fe19563be03d24dc9df5020\",\"createdAt\":\"2021-03-15T06:51:49Z\",\"defaultLanguage\":\"en\",\"email\":\"wageeshac@gmail.com\",\"firebaseId\":\"fTbGJo542TY:APA91bFnFpawVB43lmQlD2S9WVjpffZrb0zGV4miEvING_peewX0M0Luzixy0rsfLkgOYZncswWxYceNc6637S-NuksF3aGlrMAxMfnGkYFPx7dUVH7a5yK8W_qZL6qJPC1ZkYFbPWKV\",\"fullName\":\"Wageesha Chinthaka\",\"id\":\"576384eb-fc08-471b-adf1-a0ecbc632f93\",\"mobileNo\":\"0715781989\",\"preferences\":[{\"createdAt\":\"2021-03-24T10:25:06Z\",\"id\":\"aa52ba85-c70c-4f06-9405-dd3dca4f37b1\",\"preference\":{\"code\":\"mov\",\"createdAt\":\"2020-12-24T20:03:52Z\",\"id\":\"44a842cd-da2e-46e6-8e21-b5f771fd76f0\",\"isChecked\":false,\"language\":\"en\",\"name\":\"Movies\",\"updatedAt\":\"2021-01-07T12:43:21Z\"},\"preferenceID\":\"44a842cd-da2e-46e6-8e21-b5f771fd76f0\",\"updatedAt\":\"2021-03-24T10:25:06Z\",\"userAuthRef\":\"sms|5fe19563be03d24dc9df5020\"},{\"createdAt\":\"2021-03-03T12:31:02Z\",\"id\":\"cc556f5f-b436-4e17-bd84-27f2857065fb\",\"preference\":{\"code\":\"spt\",\"createdAt\":\"2020-12-24T20:03:52Z\",\"id\":\"0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be\",\"isChecked\":false,\"language\":\"en\",\"name\":\"Sport\",\"updatedAt\":\"2021-03-17T17:27:03Z\"},\"preferenceID\":\"0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be\",\"updatedAt\":\"2021-03-17T17:27:03Z\",\"userAuthRef\":\"sms|5fe19563be03d24dc9df5020\"}],\"profileBanner\":\"\",\"profileBiography\":\"Sample profile biography for wageesha edited\",\"profileDescription\":\"sample description for Wageesha\",\"profilePicture\":\"https://storage.googleapis.com/hello-app-cont/2021-03-17-hello-app-sms|5fe19563be03d24dc9df5020-IMG-20210317-WA0009.jpg\",\"profileVideo\":\"\",\"role\":\"user\",\"socialMediaProfileLink\":\"\",\"subscribeMail\":false,\"topSocialMediaPlatform\":\"\",\"updatedAt\":\"2021-03-17T17:27:03Z\",\"userName\":\"\",\"verified\":false}"

fun SharedPreferences.setAccessToken(token: String?) {
    edit { it.putString(Constant.PREF_ACCESS_TOKEN, token) }
}

fun SharedPreferences.setIdToken(token: String?) {
    edit { it.putString(Constant.PREF_ID_TOKEN, token) }
}

fun SharedPreferences.setAuthReference(authRef: String?) {
    edit { it.putString(Constant.PREF_AUTH_REFERENCE, authRef) }
}

fun SharedPreferences.getAccessToken(): String? = getString(Constant.PREF_ACCESS_TOKEN, "")

fun SharedPreferences.getIdToken(): String? = getString(Constant.PREF_ID_TOKEN, "")

//fun SharedPreferences.getIdToken() = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjhoVUNjenZpcGtuWlNOT2VjS0NQdCJ9.eyJuaWNrbmFtZSI6IiIsIm5hbWUiOiIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9jZG4uYXV0aDAuY29tL2F2YXRhcnMvZGVmYXVsdC5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0wMy0wNVQwNzoxODoyNi4zNjRaIiwicGhvbmVfbnVtYmVyIjoiKzk0NzE1NzgxOTg5IiwiaXNzIjoiaHR0cHM6Ly9kZXYtYWhhbWFkLnVzLmF1dGgwLmNvbS8iLCJzdWIiOiJzbXN8NWZlMTk1NjNiZTAzZDI0ZGM5ZGY1MDIwIiwiYXVkIjoiazRQUUpzbjlzanRZTnc2WUdReXk1V25VN0ptTXZZUXMiLCJpYXQiOjE2MTQ5Mjg3MDYsImV4cCI6MTYxNDk2NDcwNn0.dqcKRBkXasMVKkSpVbs8e8K29J9IKwm7eM1cooSisBDCnRBQGZUgGkVavGQCXJiuiKpZcsqj0dY36qViYonWLbIpxKlHgIWKMujvX3DGCK3kzOyykA9q23KwR_tmWhUEmVNaWtjPEDGwkoiKxG8MmS-7IArS8QFlWBun8fK-aHLG94_TM7edhbVA5_lWhlewgsdLEZjOv9_R_sV6HxWJuOkfI2DBwL6Urh5PT6Xst6qi9HlgFLbhmY17tbATDAKoxpfLhAHmHpid7sC3iC9iltXQezb4ms00N-YW4qkxltqE_z-smXgxzuRTAteorukuXVTFp2PQbtrhbqP4jVjEqg"

fun SharedPreferences.getAuthReference(): String? = getString(Constant.PREF_AUTH_REFERENCE, "")
