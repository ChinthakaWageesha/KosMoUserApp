package sl.com.eightdigitz.country_picker.presentation.utils


data class Resources<out T> constructor(
    val state: ResourcesState,
    val data: T? = null,
    val message: String? = null
)
