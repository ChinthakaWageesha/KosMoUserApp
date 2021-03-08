package sl.com.eightdigitz.presentation

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: PMessage? = null
)
