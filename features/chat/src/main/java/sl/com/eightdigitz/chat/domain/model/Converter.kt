package sl.com.eightdigitz.chat.domain.model

import sl.com.eightdigitz.client.models.MessageFile
import sl.com.eightdigitz.client.models.Paginator
import sl.com.eightdigitz.client.models.User


fun MessageFile.mapToDomain(): DMessageFile = DMessageFile(
    uuid = uuid,
    url = url,
    thumbnailUrl = thumbnailUrl,
    type = type
)

fun DMessageFile.mapToFCMFile(): CFile = CFile(
    file_path = url,
    thumbnail_path = thumbnailUrl,
    uuid = uuid
)

fun User.mapToDomain(): CUser = CUser(
    id = id,
    uuid = uuid,
    first_name = firstName,
    last_name = lastName,
    email = email,
    firebase_id = firebaseId,
    avatar_url = avatarUrl
)

fun Paginator.mapToDomain(): sl.com.eightdigitz.chat.domain.model.Paginator =
    sl.com.eightdigitz.chat.domain.model.Paginator(
        currentPage = currentPage,
        perPage = perPage,
        to = to
    )