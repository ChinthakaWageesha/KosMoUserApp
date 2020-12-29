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
        dob = dob,
        role = role,
        profilePicture = profilePicture,
        profileVideo = profileVideo,
        profileBanner = profileBanner,
        preferences = preferences,
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
        dob = dob,
        role = role,
        profilePicture = profilePicture,
        profileVideo = profileVideo,
        profileBanner = profileBanner,
        preferences = preferences,
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

fun SuccessResponse.mapToDataSource(): Success =
    Success(message, status, payload)