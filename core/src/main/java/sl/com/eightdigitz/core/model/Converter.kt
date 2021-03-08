package sl.com.eightdigitz.core.model

import sl.com.eightdigitz.client.apiSupports.models.*
import sl.com.eightdigitz.client.apiSupports.responses.*
import sl.com.eightdigitz.core.model.domain.*
import sl.com.eightdigitz.core.model.presentation.*
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.network.NMessage
import sl.com.eightdigitz.presentation.PMessage

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
        verified = verified,
        profileBiography = profileBiography,
        profileDescription = profileDescription,
        topSocialMediaPlatform = topSocialMediaPlatform,
        socialMediaProfileLink = socialMediaProfileLink,
        subscribeMail = subscribeMail,
        userName = userName
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
        verified = verified,
        profileBiography = profileBiography,
        profileDescription = profileDescription,
        topSocialMediaPlatform = topSocialMediaPlatform,
        socialMediaProfileLink = socialMediaProfileLink,
        subscribeMail = subscribeMail,
        userName = userName
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

fun PreferenceListResponse.mapToDomain(): ListResponse<DPreference> = ListResponse(
    message = message,
    result = status,
    data = data?.map { it.mapToDomain() }
)

fun UserListResponse.mapToDomain(): ListResponse<DUser> = ListResponse(
    message = message,
    result = status,
    data = data?.map { it.mapToDomain() }
)

fun UserSearch.mapToDomain(): DUserSearch = DUserSearch(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    userID = userID,
    searchType = searchType,
    searchText = searchText,
    profileOwnerID = profileOwnerID,
    preferenceID = preferenceID
)

fun UserPreference.mapToDomain(): DUserPreference = DUserPreference(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    userAuthRef = userAuthRef,
    user = user?.mapToDomain(),
    preferenceID = preferenceID,
    preference = preference?.mapToDomain()
)

fun DUserPreference.mapToPresentation(): PUserPreference = PUserPreference(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    userAuthRef = userAuthRef,
    user = user?.mapToPresentation(),
    preferenceID = preferenceID,
    preference = preference?.mapToPresentation()
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

fun DPreference.mapToApiModel(): Preference = Preference(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    code = code,
    name = name,
    language = language
)

fun ContactUs.mapToDomain(): DContactUs = DContactUs(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    name = name,
    email = email,
    phoneNo = phoneNo,
    subject = subject,
    description = description,
    screenshotURL = screenshotURL,
    attended = attended,
    attendedBy = attendedBy,
    attendedDate = attendedDate,
    adminComment = adminComment,
    requestSource = requestSource
)

fun JoinUs.mapToDomain(): DJoinUs = DJoinUs(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    name = name,
    email = email,
    phoneNo = phoneNo,
    topSocialMediaPlatform = topSocialMediaPlatform,
    socialMediaProfileLink = socialMediaProfileLink,
    selfDescription = selfDescription,
    profilePicURL = profilePicURL,
    attended = attended,
    attendedBy = attendedBy,
    attendedDate = attendedDate,
    adminComment = adminComment
)

fun AppInfo.mapToDomain(): DAppInfo = DAppInfo(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    countryCode = countryCode,
    languageCode = languageCode,
    description = description,
    createdBy = createdBy
)

fun Order.mapToDomain(): DOrder = DOrder(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    deletedAt = deletedAt,
    userID = userID,
    talentID = talentID,
    orderFor = orderFor,
    orderType = orderType,
    toPronoun = toPronoun,
    fromPronoun = fromPronoun,
    orderInstructions = orderInstructions,
    uploadInstructions = uploadInstructions,
    requestedDeliveryDate = requestedDeliveryDate,
    publicVideo = publicVideo,
    userReviewRate = userReviewRate,
    userComment = userComment,
    shoutOutValidUpTo = shoutOutValidUpTo,
    stage = stage
)

fun NMessage.mapToPresentation(): PMessage = PMessage(
    error = error
)


fun SuccessResponse.mapToDataSource(): Success =
    Success(message, status, payload)