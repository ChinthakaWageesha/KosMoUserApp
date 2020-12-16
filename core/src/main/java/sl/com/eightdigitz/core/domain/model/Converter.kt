package sl.com.eightdigitz.core.domain.model

import sl.com.eightdigitz.client.models.*
import sl.com.eightdigitz.core.presentation.model.*


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

/**
 * Convert presentation's  @PUser to domain's @DUser
 *
 * */
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


fun Setting.mapToDomain() = DSettings(
    id, key, value, createdAt, updatedAt
)

fun DSettings.mapToPresentation() = PSettings(
    id, key, value, createdAt, updatedAt
)