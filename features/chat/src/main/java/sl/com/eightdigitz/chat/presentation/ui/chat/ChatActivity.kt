package sl.com.eightdigitz.chat.presentation.ui.chat

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import sl.com.eightdigitz.chat.datasource.fcm.ChatManager
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_DOCUMENT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_IMAGE
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_TEXT
import sl.com.eightdigitz.chat.datasource.fcm.FCMReference.TYPE_VIDEO
import sl.com.eightdigitz.chat.di.injectChatModule
import sl.com.eightdigitz.chat.domain.model.CFile
import sl.com.eightdigitz.chat.domain.model.CUser
import sl.com.eightdigitz.chat.domain.model.ChatMessage
import sl.com.eightdigitz.chat.domain.model.DMessageFile
import sl.com.eightdigitz.chat.presentation.ui.image.PreviewActivity
import sl.com.eightdigitz.chat.presentation.ui.video.TrimVideoActivity
import sl.com.eightdigitz.chat.presentation.ui.video.VideoPreviewActivity
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState.*
import sl.com.eightdigitz.presentation.extensions.*
import sl.com.eightdigitz.chat.R
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.item_menue_friend_avatar.view.*
import net.alhazmy13.mediapicker.Image.ImagePicker
import net.alhazmy13.mediapicker.Video.VideoPicker
import org.koin.android.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.File


const val EXTRA_USER_ID = "extra_user_id"
const val EXTRA_FRIEND_ID = "extra_friend_id"
const val EXTRA_FRIEND = "extra_friend"

const val FILE_TYPE_IMAGE = "image"
const val FILE_TYPE_VIDEO = "video"
const val FILE_TYPE_FILE = "file"

const val REQ_CODE_CHAT = 1888

@RuntimePermissions
class ChatActivity : BaseActivity(),
    ChatAdapterCallback {

    private val chatViewModel by viewModel<ChatViewModel>()
    private val fcmViewModel by viewModel<FCMViewModel>()
    private lateinit var chatManager: ChatManager
    var userId: String? = null
    var friendId: String? = null
    var friend: CUser? = null
    private var file: File? = null
    private var currentUrl: Uri? = null
    private var saveItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        injectChatModule()

        intent.extras?.apply {
            userId = getString(EXTRA_USER_ID)
            friendId = getString(EXTRA_FRIEND_ID)
            friend = getParcelable(EXTRA_FRIEND)
        }

        supportActionBar?.setActionBar(
            this,
            friend?.first_name!! + " " + friend?.last_name!!,
            isHomeUpEnables = true
        )

        chatViewModel.liveDataFile.observe(this, Observer { updateMessage(it) })

        chatManager =
            ChatManager(
                this,
                userId = userId!!,
                friendId = friendId!!
            )
                .setRecyclerItems(
                    recycle_view_message_list,
                    R.layout.item_chat_message_send,
                    R.layout.item_chat_message_recieved
                )
        chatManager.callback = this
        chatManager.readMessageNormal()

        btn_chat_send.setOnClickListener {
            if (validate()) {
                var chat = ChatMessage()
                chat.message = et_chat.text.toString()
                sendMessage(chat, userId.toString(), TYPE_TEXT)
                et_chat.text.clear()
            } else {
                "Message is required.".showToast(this)
            }
        }

        btn_chat_attachment.setOnClickListener {
            alert("Select", null) {
                positiveButton("Image") {
                    ImagePicker.Builder(this@ChatActivity)
                        .mode(ImagePicker.Mode.CAMERA_AND_GALLERY)
                        .compressLevel(ImagePicker.ComperesLevel.MEDIUM)
                        .directory(ImagePicker.Directory.DEFAULT)
                        .extension(ImagePicker.Extension.JPG)
                        .scale(600, 600)
                        .allowMultipleImages(false)
                        .enableDebuggingMode(true)
                        .build()
                }
                negativeButton("Video") {
                    VideoPicker.Builder(this@ChatActivity)
                        .mode(VideoPicker.Mode.CAMERA_AND_GALLERY)
                        .directory(VideoPicker.Directory.DEFAULT)
                        .extension(VideoPicker.Extension.MP4)
                        .enableDebuggingMode(true)
                        .build()
                    showProgress()
                }
            }.show()
        }
        setResult(Activity.RESULT_OK)

    }

    private fun sendMessage(chat: ChatMessage, userID: String, type: Int) {
        chatManager.writeMessage(
            chat,
            userID,
            type
        ) { message, receiverId ->
            //Todo send push
        }
    }

    @NeedsPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    fun openFilePicker() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
        }
        startActivityForResult(intent, FILE_REQ_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // File object will not be null for RESULT_OK
                val mPaths =
                    data?.getStringArrayListExtra(ImagePicker.EXTRA_IMAGE_PATH)?.firstOrNull()
                val uriImage = Uri.parse(mPaths)
                PreviewActivity.startActivity(this, uriImage)
            }
        } else if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE) {
            hideProgress()
            if (resultCode == Activity.RESULT_OK) {
                val mPaths =
                    data?.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH)?.firstOrNull()
                mPaths?.let { TrimVideoActivity.startTrimmer(this, it) }
            }
        } else if (requestCode == TrimVideoActivity.TRIM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val image = data?.data
                file = File(image?.path)
                file?.let { file ->
                    withNetwork({
                        showProgress()
                        chatViewModel.uploadFile(file, FILE_TYPE_IMAGE)
                    }, {
                        showAlert(Msg.INTERNET_ISSUE)
                    })
                }
            }
        } else if (requestCode == PreviewActivity.REQUEST_PREVIEW) {
            if (resultCode == Activity.RESULT_OK) {
                val image = data?.data
                file = File(image?.path)
                file?.let { file ->
                    withNetwork({
                        showProgress()
                        chatViewModel.uploadFile(file, FILE_TYPE_IMAGE)
                    }, {
                        showAlert(Msg.INTERNET_ISSUE)
                    })
                }
            }
        }
    }

    private fun validate(): Boolean {
        return et_chat.text.trim().isNotEmpty()
    }

    override fun onStart() {
        super.onStart()
        chatManager.startListening()
    }

    override fun onStop() {
        chatManager.stopListening()
        super.onStop()
    }

    override fun onClickDocument(
        chatMessage: ChatMessage,
        type: Int
    ) {
        chatMessage.let { message ->
            when (type) {
                TYPE_DOCUMENT -> {
                    //Todo download and open document
                }
                TYPE_IMAGE -> {
                    var urls = arrayListOf<String>()
                    message.files?.forEach { file ->
                        urls.add(file.file_path!!)
                    }
                    StfalconImageViewer.Builder<String>(
                        this,
                        urls
                    ) { imageView: ImageView, s: String ->
                        imageView.loadImage(s)
                    }.withHiddenStatusBar(false)
                        .show(true)
                }
                TYPE_VIDEO -> {
                    message.files?.first()?.file_path?.let {
                        VideoPreviewActivity.startVideoActivity(this,
                            it
                        )
                    }
                }
                else -> {

                }
            }
        }
    }

    companion object {
        private const val REQ_CODE_GALLERY_IMAGE = 1122
        private const val REQ_CODE_CAMERA = 1123
        private const val FILE_REQ_CODE = 1124
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_chat_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        saveItem = menu.findItem(R.id.mn_friend_profile)
        val profileAvatar =
            LayoutInflater.from(this).inflate(R.layout.item_menue_friend_avatar, null, false)
                .apply {
                    setPadding(0, 0, 16, 0)
                }
        friend?.avatar_url?.let {
            profileAvatar.iv_avatar.setImage(
                it,
                R.drawable.ic_profile_picture_white
            ) { draw ->
                profileAvatar.iv_avatar.setImageDrawable(draw)
                saveItem?.actionView = profileAvatar
            }
        } ?: run {
            profileAvatar.iv_avatar.setImageResource(R.drawable.ic_profile_picture_white)
            saveItem?.actionView = profileAvatar
        }
        profileAvatar.setOnClickListener {
            friend?.let { user ->
                this.toastLong("Show profile of " + user.first_name!!, Toast.LENGTH_SHORT)
//                TODO Friend Profile View
//                ProfileActivity.startProfile(
//                    this,
//                    it1, ProfileActivity.STATE_FRIEND
//                )
            }
        }
        return true
    }

    private fun updateMessage(resource: Resource<DMessageFile>?) {
        resource?.let { it ->
            when (it.state) {
                LOADING -> {
                    showProgress()
                }
                SUCCESS -> {
                    it.data?.let { data ->
                        val type = data.type
                        data?.let { data ->
                            var fileList = arrayListOf<CFile>()

                            var file = CFile(
                                uuid = data.uuid,
                                thumbnail_path = data.thumbnailUrl,
                                file_path = data.url
                            )

                            fileList.add(file)

                            var chat = ChatMessage()
                            chat.files = fileList
                            chat.message = et_chat.text.toString()
                            sendMessage(chat, userId.toString(), TYPE_IMAGE)
                        }
                    }
                    hideProgress()
                }
                ERROR -> {
                    hideProgress()
                    showAlert(it.message.toString())
                }
            }
        }
    }

}