package sl.com.eightdigitz.chat.presentation.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.chat.domain.usecase.UserUseCase
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import sl.com.eightdigitz.presentation.utile.TempVar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(private var userUseCase: UserUseCase) : ViewModel() {

    var key: String? = null
    var currentPage: Int = 1
    var lastPage: Int? = null
    fun isLastPage() = currentPage == lastPage
    var isLoading: Boolean = false

    val liveUserListData = MutableLiveData<Resource<MutableList<CUser>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getUserList() {
        compositeDisposable.add(
            userUseCase.getUserList("",0,0)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    isLoading = true
                    liveUserListData.setLoading()
                }
                .doAfterTerminate {
                    isLoading = false
                }
                .subscribe(
                    { userResponse ->

                        userResponse.paginator?.currentPage?.let {it -> currentPage = it.toInt() }
                        userResponse.paginator?.perPage?.let {it -> TempVar.per_page = it.toInt() }
                        userResponse.paginator?.currentPage?.let {it -> currentPage = it.toInt() }

                        userResponse.paginator?.currentPage?.let { it1 -> currentPage = it1 }
                        userResponse.paginator?.to?.let { it2 -> lastPage = it2 }
                        userResponse.paginator?.perPage?.let { it3 ->
                            TempVar.per_page = it3
                        }

                        userResponse.users.let {
                            liveUserListData.setSuccess(
                                it.toMutableList()
                            )
                        }
                    },
                    {
                        liveUserListData.setError(ErrorHandler.getApiErrorMessage(it))
                    })
        )
    }
}