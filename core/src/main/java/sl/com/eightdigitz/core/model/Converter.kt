package sl.com.eightdigitz.core.model

import sl.com.eightdigitz.client.models.*
import sl.com.eightdigitz.core.model.domain.*
import sl.com.eightdigitz.core.model.presentation.*
import sl.com.eightdigitz.core.sharedPreferencesModule
import sl.com.eightdigitz.models.Success

fun User.mapToDomain(): DUser =
    DUser(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
        mobileNo = mobileNo,
        defaultLanguage = defaultLanguage,
        email = email,
        fullName = fullName,
        dob = dob,
        role = role,
        profilePicture = profilePicture,
        profileVideo = profileVideo,
        profileBanner = profileBanner,
        preferences = preferences?.map { it.mapToDomain() },
        authReference = authReference,
        verified = verified
    )

fun DUser.mapToPresentation(): PUser =
    PUser(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt,
        mobileNo = mobileNo,
        defaultLanguage = defaultLanguage,
        email = email,
        fullName = fullName,
        dob = dob,
        role = role,
        profilePicture = profilePicture,
        profileVideo = profileVideo,
        profileBanner = profileBanner,
        preferences = preferences?.map { it.mapToPresentation() },
        authReference = authReference,
        verified = verified
    )

fun OTPResponse.mapToDomain(): DOTP = DOTP(
    id = id,
    phoneNumber = phoneNumber,
    phoneVerified = phone_verified,
    requestLanguage = requestLanguage,
    error = error,
    errorDescription = errorDescription
)

fun OTPTokenResponse.mapToDomain(): DOTPToken = DOTPToken(
    accessToken = accessToken,
    idToken = idToken,
    scope = scope,
    expiresIn = expiresIn,
    tokenType = tokenType,
    error = error,
    errorDescription = errorDescription
)

fun Preference.mapToDomain(): DPreference = DPreference(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    code = code,
    name = name,
    language = language
)

fun DPreference.mapToPresentation(): PPreference = PPreference(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    code = code,
    name = name,
    language = language
)

fun SuccessResponse.mapToDataSource(): Success =
    Success(message, status, payload)