package sl.com.eightdigitz.app.presentation.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Observer
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.change_password.ChangePasswordActivity
import sl.com.eightdigitz.app.presentation.utill.FileUtil
import sl.com.eightdigitz.core.base.BaseFragment
import sl.com.eightdigitz.core.model.presentation.PUser
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*
import kotlinx.android.synthetic.main.profile_fragment.*
//import org.devio.takephoto.app.TakePhoto
//import org.devio.takephoto.app.TakePhotoImpl
//import org.devio.takephoto.compress.CompressConfig
//import org.devio.takephoto.model.CropOptions
//import org.devio.takephoto.model.InvokeParam
//import org.devio.takephoto.model.TContextWrap
//import org.devio.takephoto.model.TResult
//import org.devio.takephoto.permission.InvokeListener
//import org.devio.takephoto.permission.PermissionManager
//import org.devio.takephoto.permission.TakePhotoInvocationHandler
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ProfileFragment : BaseFragment(), View.OnClickListener /*, TakePhoto.TakeResultListener,
    InvokeListener*/ {

    companion object {
        const val TAG = "ProfileFragment"
        fun newInstance() = ProfileFragment()
    }

    private val vm by viewModel<ProfileViewModel>()
//    private var invokeParam: InvokeParam? = null
//    private var takePhoto: TakePhoto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
//        getTakePhoto()?.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated() {

        vm.liveData.observe(this, Observer { updateProfile(it) })

        btn_save.setOnClickListener(this)
        btn_change_password.setOnClickListener(this)
        btn_camera.setOnClickListener(this)
        vm.getUserData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                processSaveProfile()
            }
            R.id.btn_camera -> {
//                takePhoto?.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
//                takePhoto?.onPickFromDocuments()
//                Log.e("---", "****")
//                pictureSelector()
                chooseCameraOrGallery(v)
            }
            R.id.btn_change_password -> {
                val intent = Intent(context, ChangePasswordActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
//        getTakePhoto()?.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    private fun processSaveProfile() {
        if (isValidForm()) {
            /*activity?.withNetwork({

                val pUser = PUser(
                    name = et_first_name.getStringTrim(),
                    email = et_email.getString(),
//                            TODO update user model
                    phone = "0452643070"
                )
                vm.updateProfile(pUser)
            }, {
                Msg.INTERNET_ISSUE.showToast(context!!)
            })*/
        }
    }

//    private fun getTakePhoto(): TakePhoto? {
//        if (takePhoto == null) {
//            takePhoto =
//                TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
//        }
//        return takePhoto
//    }

    private fun isValidForm(): Boolean {
        val isEmail = et_email.validate(Msg.INVALID_EMAIL) { s -> s.isValidEmail() }
        val isFirstName =
            et_first_name.validate(getString(R.string.msg_first_name_required)) { s -> s.length > 2 }
        return isEmail && isFirstName
    }

    private fun setData(user: PUser?) {
        et_email.setText(user?.email)
        et_first_name.setText(user?.name)
        user?.avatarUrl?.let { iv_photo.loadImageRound(it) }
    }

    private fun updateProfile(resource: Resource<PUser>) {

        resource.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setData(it.data)
                    it.message?.let {
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        getTakePhoto()?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadAvatar(filePath: String?) {
        /*activity?.withNetwork({
            vm.profilePictureUpdate(File(filePath))
        }, {
            Msg.INTERNET_ISSUE.showToast(context!!)
        })*/
    }

//    override fun takeSuccess(result: TResult?) {
////        Log.e("**------**", result?.image?.compressPath)
//        result?.let {
//            it?.image?.compressPath?.let { mCompressPath ->
//                uploadAvatar(mCompressPath)
//            }
//        }
//    }
//
//    override fun takeCancel() {
//    }
//
//    override fun takeFail(result: TResult?, msg: String?) {
//        print(msg)
//    }
//
//    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
//        var type = PermissionManager.checkPermission(
//            TContextWrap.of(this), invokeParam!!.method
//        )
//        if (PermissionManager.TPermissionType.WAIT == type) {
//            this.invokeParam = invokeParam
//        }
//        return type
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        val type =
//            PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        PermissionManager.handlePermissionsResult(activity, type, invokeParam, this)
//    }

    private fun chooseCameraOrGallery(
        view: View
    ) {
        val popupMenu = PopupMenu(context!!, view)
        popupMenu.menuInflater.inflate(R.menu.menu_image_chooser_app, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {

            val mParent = FileUtil.getParentFile(context!!)
            val file = File(
                mParent,
                System.currentTimeMillis().toString() + ".jpg"
            )

            if (!mParent.exists()) {
                mParent.mkdirs()
            }
            val imageUri = Uri.fromFile(file)
            when (it.itemId) {
//                R.id.mn_choose_from_album -> {
//                    val cropOptions =
//                        CropOptions.Builder().setWithOwnCrop(true).create()
//                    takePhoto?.onEnableCompress(CompressConfig.ofDefaultConfig(), true)
//                    takePhoto?.onPickFromDocumentsWithCrop(imageUri, cropOptions)
//                    return@setOnMenuItemClickListener true
//                }
//                R.id.mn_take_photo -> {
//                    val cropOptions =
//                        CropOptions.Builder().setWithOwnCrop(true).create()
//                    takePhoto?.onEnableCompress(CompressConfig.ofDefaultConfig(), true)
//                    takePhoto?.onPickFromCaptureWithCrop(imageUri, cropOptions)
//                    return@setOnMenuItemClickListener true
//                }
                else -> return@setOnMenuItemClickListener true
            }
        }
        popupMenu.show()
    }
}
