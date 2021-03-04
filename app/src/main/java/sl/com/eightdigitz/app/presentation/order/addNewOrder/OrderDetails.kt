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
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.*

class OrderDetails : BaseActivity(), View.OnClickListener, (String) -> Unit {

    private var forList = mutableListOf<String>()
    private var mVideoFor: String? = null

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
            val intent = Intent(this, OrderInstructions::class.java)
            order.fromPronoun = mVideoFor
            order.orderFor = et_video_to.getStringTrim()
            order.toPronoun = et_address_person.getStringTrim()

            intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
            startActivity(intent)
        }
    }

    private fun validate(): Boolean {
        val isVideoTo =
            et_video_to.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 2 }

        val isAddress =
            et_address_person.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length >= 2 }

        if (!isVideoTo || !isAddress) {
            showAlert(Msg.TITLE_REQUIRED, "Please fill out the required fields.")
            return false
        }

        /*if (mVideoFor.isNullOrEmpty()){
            showAlert(Msg.TITLE_REQUIRED, "Please select to whom this video for")
            return false
        }*/

        return true
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