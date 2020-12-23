package sl.com.eightdigitz.core.model

import sl.com.eightdigitz.client.models.*
import sl.com.eightdigitz.core.model.domain.*
import sl.com.eightdigitz.core.model.presentation.*
import sl.com.eightdigitz.models.Success


fun User.mapToDomain(): DUser =
    DUser(
        id,
        uuid,
        name,
        phone,
        email,
        avatarUrl,
        timezone,
        type,
        signupError,
        business?.mapToDomain(),
        services,
        accessToken
    )

fun Business.mapToDomain() = DBusiness(
    id,
    userId,
    registeredName,
    serviceName,
    NZBN,
    registeredAddress,
    serviceAddress,
    serviceRadius,
    facebookLink,
    instagramLink,
    createdAt,
    updatedAt,
    phone,
    images?.map { it.mapToDomain() },
    services?.map { it.mapToDomain() }
)

fun Service.mapToDomain() = DService(
    id, name
)

fun Image.mapToDomain() = DImage(
    id, url, thumbnailUrl, category
)

fun DUser.mapToPresentation(): PUser =
    PUser(
        id,
        uuid,
        name,
        phone,
        email,
        avatarUrl,
        timezone,
        type,
        signupError,
        business?.mapToPresentation(),
        services,
        accessToken
    ).also {
        it.userType = userType
        it.devicePushToken = devicePushToken
        it.deviceType = deviceType
        it.deviceId = deviceId
        it.password = password
    }

fun DBusiness.mapToPresentation() = PBusiness(
    id,
    userId,
    registeredName,
    serviceName,
    NZBN,
    registeredAddress,
    serviceAddress,
    serviceRadius,
    facebookLink,
    instagramLink,
    createdAt,
    updatedAt,
    phone,
    images?.map { it.mapToPresentation() },
    services?.map { it.mapToPresentation() }
)

fun DService.mapToPresentation() = PService(
    id, name
)

fun DImage.mapToPresentation() = PImage(
    id, url, thumbnailUrl, category
)

fun Message.mapToDomain(): DMessage = DMessage(
    error = error
)

fun PUser.mapToDomain(): DUser =
    DUser(
        id, uuid, name, phone, email, avatarUrl, timezone, type, services = services
    ).also {
        it.idSM = idSM
        it.userType = userType
        it.token = token
        it.provider = provider
        it.devicePushToken = devicePushToken
        it.deviceType = deviceType
        it.deviceId = deviceId
        it.password = password
        it.message = message
    }

fun OTPResponse.mapToDomain():DOTP = DOTP(
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