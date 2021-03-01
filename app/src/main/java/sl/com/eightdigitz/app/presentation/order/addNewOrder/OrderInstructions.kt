package sl.com.eightdigitz.app.presentation.order.addNewOrder

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_order_instructions.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.extensions.setAppActionBar
import sl.com.eightdigitz.presentation.extensions.showAlert
import sl.com.eightdigitz.presentation.extensions.startActivity
import sl.com.eightdigitz.presentation.extensions.validateOnTextChange

class OrderInstructions : BaseActivity(), View.OnClickListener {

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

    private fun onNext(){
        if(validate()){
            startActivity<PaymentCheckout>()
        }
    }

    private fun validate(): Boolean{
        val isInstruction = et_instructions.validateOnTextChange(isCheckValidateIcon = true) { s -> s.length > 2 }

        if (!isInstruction) {
            showAlert(Msg.TITLE_REQUIRED, "Please fill out the required fields.")
            return false
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_order_instructions_next -> onNext()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        goBack()
        return super.onSupportNavigateUp()
    }
}