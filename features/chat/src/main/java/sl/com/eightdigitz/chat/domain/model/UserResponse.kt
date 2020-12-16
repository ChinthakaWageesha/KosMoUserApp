package sl.com.eightdigitz.chat.domain.model

data class UserResponse(
    var users: List<CUser> ,
    var paginator: Paginator? = null
)