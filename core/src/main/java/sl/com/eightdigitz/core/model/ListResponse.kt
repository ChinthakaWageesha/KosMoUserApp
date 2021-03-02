package sl.com.eightdigitz.core.model

import sl.com.eightdigitz.client.apiSupports.models.Message


class ListResponse<T>(
    var message: Message? = null,
    var result: Boolean? = null,
    var data: List<T>? = null
)