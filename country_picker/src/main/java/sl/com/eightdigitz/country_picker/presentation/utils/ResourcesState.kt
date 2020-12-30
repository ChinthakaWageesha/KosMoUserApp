package sl.com.eightdigitz.country_picker.presentation.utils

sealed class ResourcesState {
    object LOADING : ResourcesState()
    object SUCCESS : ResourcesState()
    object ERROR : ResourcesState()
}
