package sl.com.eightdigitz.app.presentation.search.preferenceSearch

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_talent_by_preference.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.IntentParsableConstants
import sl.com.eightdigitz.presentation.extensions.setAppActionBar

class SearchTalentByPreference : BaseActivity() {

    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_talent_by_preference)
        init()
    }

    private fun init() {
        if (intent.hasExtra(IntentParsableConstants.EXTRA_PREFERENCE)) {
            title = intent.getStringExtra(IntentParsableConstants.EXTRA_PREFERENCE)
        }

        setToolbar()
        setAdapter()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            title!!,
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
}