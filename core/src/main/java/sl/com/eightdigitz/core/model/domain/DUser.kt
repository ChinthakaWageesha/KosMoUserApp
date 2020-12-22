package sl.com.eightdigitz.core.model.domain


/**
 * @property id
 * @property uuid
 * @property firstName
 * @property lastName
 * @property fullName
 * @property phone
 * @property email
 * @property avatarUrl
 * @property address
 * @property accessToken
 *
 */

data class DUser(
    var id: Int? = null,
    var uuid: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var avatarUrl: String? = null,
    var timezone: String? = null,
    var type: Int? = null,
    var signupError: String? = null,
    var business: DBusiness? = null,
    var services: String? = null,
    var accessToken: String? = null
) {
    var userType: String? = null
    var idSM: String? = null
    var token: String? = null
    var provider: String? = null
    var deviceId: String? = null
    var deviceType: String? = null
    var password: String? = null
    var devicePushToken: String? = null
    var message: String? = null
}