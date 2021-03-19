package sl.com.eightdigitz.app.presentation.favourite

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favourites.*
import sl.com.eightdigitz.app.R
import sl.com.eightdigitz.core.base.BaseActivity
import sl.com.eightdigitz.presentation.Constant
import sl.com.eightdigitz.presentation.extensions.setAppActionBar

class Favourites : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        setToolbar()
        init()
    }

    private fun setToolbar() {
        supportActionBar?.setAppActionBar(
            this,
            title = getString(sl.com.eightdigitz.presentation.R.string.title_your_favourites),
            isHomeUpEnables = true
        )
    }

    private fun init() {
        rv_favourites.adapter = FavouritesAdapter(
            imageUrl = Constant.KID_IMAGE_URL
        )
        rv_favourites.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}