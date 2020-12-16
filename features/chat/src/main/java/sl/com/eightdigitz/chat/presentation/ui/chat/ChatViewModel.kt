package sl.com.eightdigitz.chat.presentation.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sl.com.eightdigitz.models.Success
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.chat.datasource.fcm.ChatManager
import sl.com.eightdigitz.chat.datasource.fcm.FirebaseRemote
import sl.com.eightdigitz.chat.domain.model.ChatMessage
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.chat.domain.model.DMessageFile
import sl.com.eightdigitz.chat.domain.usecase.FileUploadUseCase
import sl.com.eightdigitz.network.ErrorHandler
import sl.com.eightdigitz.presentation.setError
import sl.com.eightdigitz.presentation.setLoading
import sl.com.eightdigitz.presentation.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

//Todo api integration
class ChatViewModel(
//        val profileUseCase: ProfileUseCase,
//        val chatUseCase: ChatUseCase,
        val fileUploadUseCase: FileUploadUseCase
) : ViewModel() {

    val liveUserListData = MutableLiveData<Resource<MutableList<CUser>>>()
    val liveDataRecent: MutableLiveData<ChatMessage> = MutableLiveData()
    val liveDataFile = MutableLiveData<Resource<DMessageFile>>()
    val liveDataUnfriend = MutableLiveData<Resource<Success>>()

    var key: String? = null
    var currentPage: Int = 1
    var lastPage: Int? = null
    fun isLastPage() = currentPage == lastPage
    var isLoading: Boolean = false

    private var firebaseRemote: FirebaseRemote =
        FirebaseRemote()
    private val compositeDisposable = CompositeDisposable()

    /*fun getUserList(): LiveData<List<User>> {
        firebaseRemote.readUser(liveData)
        return liveData
    }*/

    fun getUser(): LiveData<CUser> {
//        val pUser = profileUseCase.getProfileFromSp()?.mapToPresentation()
        val mutableLiveData = MutableLiveData<CUser>()
//        mutableLiveData.value = pUser
        return mutableLiveData
    }

    fun getRecent(path: String): LiveData<ChatMessage> {
        firebaseRemote.getRecent(path) {
            liveDataRecent.value = it
        }
        return liveDataRecent
    }

    fun removeRecent(userId: String, friendId: String) {
        firebaseRemote.deleteRecent(ChatManager.getPath(userId, friendId, true))
        ChatManager.deleteMessages(
            ChatManager.getPath(userId, friendId, false))
    }


    /**
     * Upload and share files via the chat
     * Supported file types: doc,pdf,docx,jpg,jpeg,png,gif,mpga,mp3,wav,mp4,mov,ogg,qt
     * Implemented: jpg,jpeg,png
     *
     * */
    fun uploadFile(file: File, fileType: String) {

        val requestFile = RequestBody.create((MultipartBody.FORM), file)
        val requestBody = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val requestBodyType = RequestBody.create(MultipartBody.FORM, fileType)

        compositeDisposable.add(
            fileUploadUseCase.uploadFile(requestBody, null)
                .doOnSubscribe { liveDataFile.setLoading() }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        liveDataFile.setSuccess(it, null)
                    },
                    {
                        liveDataFile.setError(ErrorHandler.getApiErrorMessage(it))
                    })
        )
    }

    fun sendPush(message: String, recieverId: String) {
//        compositeDisposable.add(
//            chatUseCase.chatsMessagesPush(message, recieverId)
//                .subscribeOn(Schedulers.io())
//                .subscribe()
//        )
    }

    override fun onCleared() {
        firebaseRemote.destroy()
        compositeDisposable.dispose()
        super.onCleared()
    }

}