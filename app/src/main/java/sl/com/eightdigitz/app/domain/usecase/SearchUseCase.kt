package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.SearchRepository

class SearchUseCase(private val searchRepository: SearchRepository) {

    fun getHomeCategories() = searchRepository.getPreferenceCategories()

    fun getRecentSearchedTalents(userId: String) = searchRepository.getRecentSearchedTalents(userId)
}