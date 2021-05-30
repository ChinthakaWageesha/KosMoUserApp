package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.ExploreRepository

class ExploreUseCase(private val exploreRepository: ExploreRepository) {

    fun getExploreFeed(preference:  Array<String>?) = exploreRepository.getExploreFeed(preference)
}