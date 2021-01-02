package sl.com.eightdigitz.presentation.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import sl.com.eightdigitz.presentation.R

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString().trim())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s.toString().trim())
        }
    })
}

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

fun EditText.validateOnTextChange(
    message: String? = "error",
    isCheckValidateIcon: Boolean = false,
    validator: (String) -> Boolean
): Boolean {
    this.onTextChanged {
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

    if (isCheckValidateIcon) {
        if (errorMessage == null) {
            this.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                this.context.getCompatDrawable(R.drawable.ic_text_field_check),
                null
            )
            this.setBackgroundResource(R.drawable.bg_edit_text_boarder_corner_round_3dp)
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            )
            this.setBackgroundResource(R.drawable.bg_edit_text_error_boarder_corner_round_3dp)
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
