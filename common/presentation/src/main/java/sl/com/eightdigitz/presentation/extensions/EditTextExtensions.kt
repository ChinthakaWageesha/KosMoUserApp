package sl.com.eightdigitz.presentation.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * This extension supports EditText validation such as emails, maximum and minimum length
 * inspired from
 * @see https://proandroiddev.com/easy-edittext-content-validation-with-kotlin-316d835d25b3
 * */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString().trim())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

/**
 * You can validate the EditText like
 *
 * et_email.validate("Valid email address required"){ s -> s.isValidEmail() }
 *
 * et_email.validate("Minimum length is 6"){ s-> s.length>=6 }
 *
 * */
fun EditText.validate(message: String, validator: (String) -> Boolean): Boolean {
    this.afterTextChanged {
        this.error = if (validator(it)) null else message
    }
    this.error = if (validator(this.getString())) null else message

    return validator(this.getString())
}

fun EditText.validate(
    message: String? = "error",
    isCheckValidateIcon: Boolean = false,
    validator: (String) -> Boolean
): Boolean {
    this.afterTextChanged {
        val errorMessage = if (validator(it)) null else message
        backGroundRunTime(errorMessage, isCheckValidateIcon)
    }
    val errorMessage = if (validator(this.getString())) null else message
    backGroundRunTime(errorMessage, isCheckValidateIcon)

    return validator(this.getString())
}

private fun EditText.backGroundRunTime(
    errorMessage: String?,
    isCheckValidateIcon: Boolean
) {

    /* this.backgroundTintList = if (errorMessage == null) {
         this.context.getCompatColorState(R.color.edit_text_normal_state)
     } else {
         this.context.getCompatColorState(R.color.edit_text_error_state)
     }*/
    if (isCheckValidateIcon) {
        if (errorMessage == null) {
            this.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                this.context.getCompatDrawable(sl.com.eightdigitz.presentation.R.drawable.ic_text_field_check),
                null
            )
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            )
        }
    }
}

/**
 * @return String value of the EditTextView
 * */
fun EditText.getString(): String {
    return this.text.toString()
}

/**
 * @return Trimmed String value of the EditTextView
 * */
fun EditText.getStringTrim(): String {
    return this.getString().trim()
}
