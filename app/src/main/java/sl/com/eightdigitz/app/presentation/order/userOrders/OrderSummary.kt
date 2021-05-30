package sl.com.eightdigitz.app.presentation.order.userOrders

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_order_summary.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.order.OrderViewModel
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.*
import sl.com.eightdigitz.presentation.extensions.*

class OrderSummary : BaseActivity() {

    private val vmOrder by viewModel<OrderViewModel>()
    private var order: DOrder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_order_summary),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER)) {
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)

            vmOrder.getTalentById(order?.talentID!!)
        }
        vmOrder.liveDataTalent.observe(this, Observer { observerGetTalent(it) })
    }

    private fun setData(talent: DUser) {
        sv_order_summary.makeVisible()
        tv_no_talent_order.makeGone()

        setOrderStages()
        if (!talent.profilePicture.isNullOrEmpty()) {
            iv_order_summary_talent_image.setRoundedImage(
                url = talent.profilePicture!!,
                radius = 12
            )
        } else {
            iv_order_summary_talent_image.setRoundedImage(
                url = Constant.USER_IMAGE_AQUAMAN,
                radius = 12
            )
        }

        tv_order_summary_talent_name.text = talent.fullName

        if (!talent.preferences.isNullOrEmpty()) {
            for (i in talent.preferences!!.indices) {
                if (talent.preferences!![i].preferenceID == "44a842cd-da2e-46e6-8e21-b5f771fd76f0") {
                    tv_order_summary_talent_field.text = "Movies"
                }

                if (talent.preferences!![i].preferenceID == "0d82f1fe-4c8f-4201-8f3c-dd4355d8e4be") {
                    tv_order_summary_talent_field.text = "Sports"
                }
            }
        } else {
            tv_order_summary_talent_field.text = "Movies"
        }

        if (order?.orderReference.isNullOrEmpty()){
            tv_order_summary_reference.text = "Order Reference ${order?.id!!.split("-")[0]}"
        } else {
            tv_order_summary_reference.text = "Order Reference ${order?.orderReference!!}"
        }

        tv_order_summary_due_date.text = "Due by ${order?.requestedDeliveryDate}"
        tv_order_summary_for_username.text = order?.orderFor
        tv_address_as.text = "Address ${order?.orderFor} as"
        tv_address_order_summary_user_as.text = order?.toPronoun
        tv_order_summary_message.text = order?.orderType

        tv_title_instructions.text = "Your instructions for ${order?.orderFor} are"
        tv_order_summary_instructions.text = order?.orderInstructions
    }


    private fun setOrderStages() {
        if (order?.stage!! == "New") {
            makeReviewingEnable()
        }

        if (order?.stage!! == "OrderAccepted") {
            makeReviewingEnable()
            makeProcessingEnable()
        }

        if (order?.stage!! == "OnHold") {
            makeReviewingEnable()
            makeProcessingEnable()
            makeOnHoldEnable()
        }

        if (order?.stage!! == "TalentAccepted" || order?.stage!! == "OrderReviewSuccess" || order?.stage!! == "Delivered") {
            makeReviewingEnable()
            makeProcessingEnable()
            makeOnHoldEnable()
            makeApprovedEnable()
        }

        if (order?.stage!! == "Expired") {
            makeReviewingEnable()
            makeProcessingEnable()
            makeOnHoldEnable()
            makeApprovedEnable()
            makeExpiredEnable()
        }

        if (order?.stage!! == "OrderRejected" || order?.stage!! == "TalentRejected" || order?.stage!! == "OrderReviewRejected") {
            makeReviewingEnable()
            makeProcessingEnable()
            makeOnHoldEnable()
            makeApprovedEnable()
            makeExpiredEnable()
            makeRefundEnable()
        }
    }

    private fun makeReviewingEnable() {
        cv_reviewing_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_pink_oval
        )
        cv_reviewing_order.alpha = 1f
        tv_reviewing_order.alpha = 1f
    }

    private fun makeProcessingEnable() {
        cv_processing_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_dark_blue_oval
        )
        cv_processing_order.alpha = 1f
        tv_processing_order.alpha = 1f
    }

    private fun makeOnHoldEnable() {
        cv_on_hold_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_orange_oval
        )
        cv_on_hold_order.alpha = 1f
        tv_on_hold_order.alpha = 1f
    }

    private fun makeApprovedEnable() {
        cv_approved_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_pink_oval
        )
        cv_approved_order.alpha = 1f
        tv_approved_order.alpha = 1f
    }

    private fun makeExpiredEnable() {
        cv_expired_order.alpha = 1f
        tv_expired_order.alpha = 1f
    }

    private fun makeRefundEnable() {
        cv_refund_order.setBackgroundResource(
            sl.com.eightdigitz.presentation.R.drawable.bg_light_green_oval
        )
        cv_refund_order.alpha = 1f
        tv_refund_order.alpha = 1f
    }


    private fun observerGetTalent(resource: Resource<DUser>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setData(it.data!!)
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    if (it.errorCode == 404) {
                        sv_order_summary.makeGone()
                        tv_no_talent_order.makeVisible()
                    } else {
                        it.message?.error.toString().showToast(this)
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                setResult(ResultCodes.NEW_ORDER_RESULT_CODE).also {
                    onBackPressed()
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}