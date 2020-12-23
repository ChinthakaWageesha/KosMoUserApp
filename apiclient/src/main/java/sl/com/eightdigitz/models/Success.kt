package sl.com.eightdigitz.models

import sl.com.eightdigitz.client.models.Message

/**
 * Created by Chathura Wijesinghe on 2019-06-20.
 * Elegantmedia
 * chaturaw.emedia@gmail.com
 */

data class Success(
    var message: Message?,
    var status: Boolean?,
    var payload: Any?
)
