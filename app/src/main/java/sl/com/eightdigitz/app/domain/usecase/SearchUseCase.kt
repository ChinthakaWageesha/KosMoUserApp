package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.SearchRepository
import sl.com.eightdigitz.client.apiSupports.requests.LogSearchRequest

class SearchUseCase(private val searchRepository: SearchRepository) {

    fun getSearchCategories() = searchRepository.getPreferenceCategories()

    fun getRecentSearchedTalents() = searchRepository.getRecentSearchedTalents()

    fun getTalentsByPreference(preferenceId: String) =
        searchRepository.getTalentsByPreference(preferenceId)

    fun logSearch(logSearchRequest: LogSearchRequest) =
        searchRepository.logSearch(logSearchRequest)

    fun removeRecentViewedProfile(
        ownerId: String,
        searchType: String
    ) = searchRepository.removeRecentViewedProfile(
        ownerId = ownerId,
        searchType = searchType
    )
}