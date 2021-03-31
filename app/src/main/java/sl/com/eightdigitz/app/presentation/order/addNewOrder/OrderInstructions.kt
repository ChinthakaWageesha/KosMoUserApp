package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_order_instructions.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.RequestCodes
import sl.com.eightdigitz.presentation.ResultCodes
import sl.com.eightdigitz.presentation.extensions.*
import javax.xml.transform.dom.DOMResult

class OrderInstructions : BaseActivity(), View.OnClickListener {

    var order: DOrder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_instructions)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            getString(sl.com.eightdigitz.presentation.R.string.title_instructions),
            isHomeUpEnables = true
        )
    }

    private fun init() {

        if (intent.hasExtra(IntentParsableConstants.EXTRA_NEW_ORDER) &&
            intent.getParcelableExtra<DOrder>(IntentParsableConstants.EXTRA_NEW_ORDER) != null
        ) {
            order = intent.getParcelableExtra(IntentParsableConstants.EXTRA_NEW_ORDER)
        }


        et_instructions.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (sequence!!.length < 250) {
                    tv_instruction_char_count.text = sequence.length.toString()
                    et_instructions.setTextColor(
                        ContextCompat.getColor(
                            this@OrderInstructions,
                            sl.com.eightdigitz.presentation.R.color.colorTextBlack
                        )
                    )
                } else {
                    tv_instruction_char_count.text = "250"
                    et_instructions.setTextColor(
                        ContextCompat.getColor(
                            this@OrderInstructions,
                            sl.com.eightdigitz.presentation.R.color.colorRed
                        )
                    )
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        btn_order_instructions_next.setOnClickListener(this)
    }

    private fun onNext() {
        if (validate()) {
            order?.orderInstructions = et_instructions.getStringTrim()
            val intent = Intent(this, PaymentCheckout::class.java)
            intent.putExtra(IntentParsableConstants.EXTRA_NEW_ORDER, order)
            startActivityForResult(intent, RequestCodes.NEW_ORDER_REQUEST_CODE)
        }
    }

    private fun validate(): Boolean {
        val isInstruction =
            et_instructions.validateAppEditTextOnTextChange(isCheckValidateIcon = true) { s -> s.length > 2 }

        if (!isInstruction) {
            showAlert(Msg.TITLE_REQUIRED, "Please fill out the required fields.")
            return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RequestCodes.NEW_ORDER_REQUEST_CODE && resultCode == ResultCodes.NEW_ORDER_RESULT_CODE){
            setResult(ResultCodes.NEW_ORDER_RESULT_CODE).also {
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_order_instructions_next -> onNext()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        hideKeyboard()
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}