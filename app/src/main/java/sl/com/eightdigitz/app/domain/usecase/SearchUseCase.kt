package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.SearchRepository

class SearchUseCase(private val searchRepository: SearchRepository) {

    fun getSearchCategories() = searchRepository.getPreferenceCategories()

    fun getRecentSearchedTalents() = searchRepository.getRecentSearchedTalents()

    fun removeRecentViewedProfile(
        ownerId: String,
        searchType: String
    ) = searchRepository.removeRecentViewedProfile(
        ownerId = ownerId,
        searchType = searchType
    )

    fun getTalentsByPreference(preferenceId: String) =
        searchRepository.getTalentsByPreference(preferenceId)
}