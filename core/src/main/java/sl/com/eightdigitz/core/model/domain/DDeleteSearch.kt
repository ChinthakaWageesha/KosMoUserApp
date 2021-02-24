package sl.com.eightdigitz.core.model.domain

data class DDeleteSearch(
    var id: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null,
    var deletedAt: String? = null,
    var userID: String? = null,
    var searchType: String? = null,
    var searchText: String? = null,
    var profileOwnerID: String? = null,
    var preferenceID: String? = null
)
