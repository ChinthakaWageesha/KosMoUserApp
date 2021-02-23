package sl.com.eightdigitz.app.presentation.search.preferenceSearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_talent_by_preference.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.*

class SearchTalentByPreference : BaseActivity(), View.OnClickListener {

    private var preference: DPreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_talent_by_preference)
        init()
    }

    private fun init() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_PREFERENCE)) {
            preference = intent.getParcelableExtra(IntentParsableConstants.EXTRA_PREFERENCE)
        }

        setToolbar()
        setAdapter()
        et_search_talent_by_preference.clearFocus()
        et_search_talent_by_preference.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (sequence!!.isNotEmpty()){
                    tv_cancel.makeVisible()
                } else {
                    tv_cancel.makeGone()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        tv_cancel.setOnClickListener(this)
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            preference?.name!!,
            isHomeUpEnables = true
        )
    }

    private fun setAdapter() {
        rv_search_talent_preference.adapter = SearchTalentByPreferenceAdapter()
        rv_search_talent_preference.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_cancel -> et_search_talent_by_preference.clearText()
        }
    }
}