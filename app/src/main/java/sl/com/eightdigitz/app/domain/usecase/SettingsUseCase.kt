package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.SettingsRepository

class SettingsUseCase(
    private val settingsRepository: SettingsRepository
) {

    fun logout() = settingsRepository.logout()

}