package sl.com.eightdigitz.models

import sl.com.eightdigitz.client.models.Message

data class Success(
    var message: Message?,
    var status: Boolean?,
    var payload: Any?
)
