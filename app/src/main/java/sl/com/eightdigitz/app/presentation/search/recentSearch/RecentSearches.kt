package sl.com.eightdigitz.app.presentation.search.recentSearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recent_searches.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.extensions.clearText
import sl.com.eightdigitz.presentation.extensions.hideKeyboard
import sl.com.eightdigitz.presentation.extensions.makeGone
import sl.com.eightdigitz.presentation.extensions.makeVisible

class RecentSearches : BaseActivity(), View.OnClickListener {

    private var searchKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_searches)
        init()
    }

    private fun init() {
        et_recent_searches.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchKey = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        setAdapter()
        tv_cancel_recent_searches.setOnClickListener(this)
    }

    private fun setAdapter() {
        rv_recent_talent_searches.adapter = RecentSearchAdapter()
        rv_recent_talent_searches.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_cancel_recent_searches -> {
                if (searchKey.isNotEmpty()) {
                    et_recent_searches.clearText()
                } else {
                    hideKeyboard()
                    onBackPressed()
                }
            }
        }
    }
}