import androidx.lifecycle.MutableLiveData
import sl.com.eightdigitz.country_picker.presentation.utils.Resources
import sl.com.eightdigitz.country_picker.presentation.utils.ResourcesState

fun <T> MutableLiveData<Resources<T>>.setSuccess(data: T, message: String? = null) =
    postValue(Resources(ResourcesState.SUCCESS, data, message))

fun <T> MutableLiveData<Resources<T>>.setLoading() =
    postValue(
        Resources(
            ResourcesState.LOADING,
            value?.data
        )
    )

fun <T> MutableLiveData<Resources<T>>.setError(message: String? = null) =
    postValue(
        Resources(
            ResourcesState.ERROR,
            value?.data,
            message
        )
    )
