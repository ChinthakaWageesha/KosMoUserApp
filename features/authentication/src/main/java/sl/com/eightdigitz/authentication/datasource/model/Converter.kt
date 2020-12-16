package sl.com.eightdigitz.authentication.datasource.model

import sl.com.eightdigitz.client.models.PasswordEdit
import sl.com.eightdigitz.client.models.PasswordEmail

fun mapToApiPasswordEdit(oldPassword: String, password: String): PasswordEdit =
    PasswordEdit(
        currentPassword = oldPassword,
        password = password,
        passwordConfirmation = password
    )

fun mapToApiPasswordEmail(email: String): PasswordEmail = PasswordEmail(email = email)

fun sl.com.eightdigitz.client.models.SuccessResponse.mapToDataSource(): Success =
    Success(message, result, payload)
