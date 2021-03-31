package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_order_details.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.RequestCodes
import sl.com.eightdigitz.presentation.ResultCodes
import sl.com.eightdigitz.presentation.extensions.*

class OrderDetails : BaseActivity(), View.OnClickListener, (String) -> Unit {

    private var forList = mutableListOf<String>()
    private var mVideoFor: String? = null
    private var talentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_order_details),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        setAdapter()

        if (intent.hasExtra(IntentParsableConstants.EXTRA_USER) &&
            intent.getParcelableExtra<DUser>(IntentParsableConstants.EXTRA_USER) != null
        ) {
            talentId = intent.getParcelableExtra<DUser>(IntentParsableConstants.EXTRA_USER)?.id
        }

        et_video_to.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (sequence!!.length > 2) {
                    tv_order_to_name.text = sequence.toString()
                } else {
                    tv_order_to_name.text = ""
                }
            }

            override fun afterTextChanged(c: Editable?) {

            }

        })
        btn_order_detail_next.setOnClickListener(this)
    }

    private fun setAdapter() {
        forList.add(0, "It's for me")
        forList.add(1, "Someone else")
        forList.add(2, "Brand")
        forList.add(3, "For my business")
        forList.add(4, "For a guest")
        rv_video_for.adapter = VideoForAdapter(forList, this)
        rv_video_for.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun onNext() {
        if (validate()) {
            val order = DOrder()
            order.talentID = talentId
            order.fromPronoun = mVideoFor
            order.orderFor = et_video_to.getStringTrim()
            order.toPronoun = et_address_person.getStringTrim()

            val intent = Intent(this, OrderInstructions::class.java)
            intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
            startActivityForResult(intent, RequestCodes.NEW_ORDER_REQUEST_CODE)
        }
    }

    private fun validate(): Boolean {
        val isVideoTo =
            et_video_to.validateAppEditTextOnTextChange(isCheckValidateIcon = true) { s -> s.length > 2 }

        val isAddress =
            et_address_person.validateAppEditTextOnTextChange(isCheckValidateIcon = true) { s -> s.length >= 2 }

        if (!isVideoTo || !isAddress) {
            showAlert(Msg.TITLE_REQUIRED, "Please fill out the required fields.")
            return false
        }

        if (mVideoFor.isNullOrEmpty()) {
            showAlert(Msg.TITLE_REQUIRED, "Please select to whom this video for")
            return false
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.NEW_ORDER_REQUEST_CODE && resultCode == ResultCodes.NEW_ORDER_RESULT_CODE) {
            onBackPressed()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_order_detail_next -> onNext()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun invoke(videoFor: String) {
        mVideoFor = videoFor
    }
}