package sl.com.eightdigitz.app.presentation.search.preferenceSearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_talent_by_preference.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.app.presentation.search.SearchViewModel
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.core.model.domain.DPreference
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.Msg
import sl.com.eightdigitz.presentation.Resource
import sl.com.eightdigitz.presentation.ResourceState
import sl.com.eightdigitz.presentation.extensions.*

class SearchTalentByPreference : BaseActivity(), View.OnClickListener {

    private val vm by viewModel<SearchViewModel>()
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
        getTalentsByPreference()

        et_search_talent_by_preference.clearFocus()
        et_search_talent_by_preference.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (sequence!!.isNotEmpty()) {
                    tv_cancel.makeVisible()
                } else {
                    tv_cancel.makeGone()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        vm.liveDataTalentByPreferences.observe(this, Observer { observerTalentsByPreference(it) })
        tv_cancel.setOnClickListener(this)
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            preference?.name!!,
            isHomeUpEnables = true
        )
    }

    private fun getTalentsByPreference() {
        withNetwork({
            vm.getTalentsByPreference(preference?.id!!)
        }, {
            showAlert(message = Msg.INTERNET_ISSUE)
        })
    }

    private fun observerTalentsByPreference(resource: Resource<List<DUser>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setAdapter(it.data!!.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showAlert(Msg.TITLE_ERROR, it.message.toString())
                }
            }
        }
    }

    private fun setAdapter(talentList: MutableList<DUser>) {
        rv_search_talent_preference.adapter = SearchTalentByPreferenceAdapter(talentList)
        rv_search_talent_preference.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        goBack()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_cancel -> et_search_talent_by_preference.clearText()
        }
    }
}