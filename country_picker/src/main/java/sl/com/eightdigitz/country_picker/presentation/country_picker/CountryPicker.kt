package sl.com.eightdigitz.country_picker.presentation.country_picker

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.country_picker.injectFeature
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.country_picker.presentation.utils.Resources
import sl.com.eightdigitz.country_picker.presentation.utils.ResourcesState
import kotlinx.android.synthetic.main.activity_country_picker.*
import kotlinx.android.synthetic.main.partial_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import sl.com.eightdigitz.country_picker.R
import java.util.*


class CountryPicker : AppCompatActivity() {

    private lateinit var mViewAdapter: CountryPickerAdapter
    private lateinit var mViewManager: RecyclerView.LayoutManager
    private val mCountriesViewModel by viewModel<CountriesViewModel>()
    private lateinit var mLocationManager: LocationManager
    private var mCountryString: String? = null
    private var mAllCountryList: MutableList<PCountry>? = null
    private var progress: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_picker)
        injectFeature()
        mCountriesViewModel.mLiveData.observe(this, Observer { getCountryList(it) })
        mLocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(PickerManager.activityUpIcon)
        supportActionBar?.setTitle(PickerManager.activityTitle)

        supportActionBar?.let {
            val mSpannableText: SpannableString? = SpannableString(supportActionBar?.title)
            mSpannableText?.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        this,
                        PickerManager.activityTitleTextColor
                    )
                ),
                0,
                mSpannableText.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            supportActionBar?.title = mSpannableText
        }
        setToolBar()
        setupSearch()

        mCountriesViewModel.getCountries()
        setStatusBarColor()
        edit_search.setCompoundDrawablesWithIntrinsicBounds(
            PickerManager.activitySearchIcon,
            0,
            0,
            0
        )

        progress = Dialog(this).apply {
            setContentView(R.layout.item_progress)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    /**
     *
     */
    private fun setupSearch() {
        edit_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                mViewAdapter.filter.filter(p0.toString())

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mViewAdapter.filter.filter(p0.toString())
            }
        })
    }


    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor =
                ContextCompat.getColor(this@CountryPicker, PickerManager.activityStatusBarColor)
        }
    }


    //set the toolbar as actionbar in case the app theme does not have a toolbar
    private fun setToolBar() {
        if (supportActionBar == null) {
            layout_toolbar.visibility = View.VISIBLE
            setSupportActionBar(toolbar)
            val typedValueToolbarBg = TypedValue()
            this.theme.resolveAttribute(
                R.attr.countryToolbarBackgroundColor,
                typedValueToolbarBg,
                true
            )
            val colorToolbarBackgroundResId = typedValueToolbarBg.resourceId

            val typedValueToolTextClr = TypedValue()
            this.theme.resolveAttribute(
                R.attr.countryPickerTitleTextColor,
                typedValueToolTextClr,
                true
            )
            val colorToolbarTextClrResId = typedValueToolTextClr.resourceId

            text_toolbar_title.text = getString(PickerManager.activityTitle)
            text_toolbar_title.setTextColor(
                ContextCompat.getColor(
                    this@CountryPicker,
                    colorToolbarTextClrResId
                )
            )

            image_back.setImageResource(PickerManager.activityUpIcon)
            image_back.setOnClickListener {
                onBackPressed()
            }

            toolbar.background =
                ColorDrawable(
                    ContextCompat.getColor(
                        this@CountryPicker,
                        colorToolbarBackgroundResId
                    )
                )

        } else
            layout_toolbar.visibility = View.GONE

    }


    private fun getCountryList(mResource: Resources<MutableList<PCountry>>?) {
        mResource?.let {
            when (it.state) {
                ResourcesState.LOADING -> progress?.show()
                ResourcesState.SUCCESS -> {
                    mAllCountryList = it.data
                    mCountryString = getUserCountry()
                    setUpData(it.data)
                    progress?.dismiss()


                }
                ResourcesState.ERROR -> {
                    progress?.dismiss()
                    showMessage(getString(R.string.msg_fetch_failed))
                }
            }

        }
    }


    private fun getUserCountry(): String? {
        if (PickerManager.mCurrentDialCode != null) {
            return findDialCodeFrmCountryCode(PickerManager.mCurrentDialCode)

        } else {
            val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val simCountry = tm.simCountryIso
            if (simCountry != null && simCountry.length == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US)
            } else if (tm.phoneType != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                val networkCountry = tm.networkCountryIso
                if (networkCountry != null && networkCountry.length == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US)
                }
            }

        }
        return null
    }


    // finds the country code(ex -> lk,in) from dialcode
    private fun findDialCodeFrmCountryCode(mDialoCode: String?): String? {
        var mItem: PCountry? = null
        mAllCountryList?.let { mutableList ->
            mItem = mutableList.find {
                it.dialCode == mDialoCode?.trim()
            }
        }

        return mItem?.code

    }


    private fun setUpData(mData: MutableList<PCountry>?) {
        mAllCountryList = mData

        val mUserCountry: PCountry? = mData?.find { mPCountry ->
            mPCountry.code.toString().equals(mCountryString.toString(), ignoreCase = true)
        }

        mCountryString = mUserCountry?.name

        mData?.sortBy { mPCountry -> mPCountry.name }
        mViewAdapter = CountryPickerAdapter(this,
            mCountryString, object : CountryPickerAdapter.VisibleCountryCountListener {
                override fun onCountryCountChange(mCount: Int) {
                    if (mCount == 0)
                        tv_empty_view.visibility = View.VISIBLE
                    else
                        tv_empty_view.visibility = View.GONE
                }
            }
        )

        mUserCountry?.let {
            mData.add(0, mUserCountry)
        }

        mViewAdapter.setCountryItems(mData as MutableList<PCountry>)
        mViewAdapter.setOnCountrySelectListener(object :
            CountryPickerAdapter.CountrySelectionListener {
            override fun onCountrySelection(mCountry: PCountry) {
                val mResultIntent = Intent()
                mResultIntent.putExtra(PickerManager.activityResultKey, mCountry)
                setResult(Activity.RESULT_OK, mResultIntent)
                finish()
            }
        })
        recycle_country_list.adapter = mViewAdapter
        mViewManager = LinearLayoutManager(this)
        setEmptyDataView()

    }


    private fun setEmptyDataView() {
        if (mViewAdapter.itemCount == 0) {
            recycle_country_list.visibility = View.GONE
            tv_empty_view.visibility = View.VISIBLE

        } else {
            recycle_country_list.visibility = View.VISIBLE
            tv_empty_view.visibility = View.GONE
            recycle_country_list.apply {
                setHasFixedSize(true)
                layoutManager = mViewManager
                adapter = mViewAdapter
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    private fun showMessage(mMessage: String) =
        Toast.makeText(this, mMessage, Toast.LENGTH_SHORT).show()
}