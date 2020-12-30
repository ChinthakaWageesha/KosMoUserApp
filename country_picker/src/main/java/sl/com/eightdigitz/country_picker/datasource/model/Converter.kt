package sl.com.eightdigitz.country_picker.datasource.model

import sl.com.eightdigitz.country_picker.domain.model.DCountry
import sl.com.eightdigitz.country_picker.presentation.models.PCountry

fun MutableList<DCountry>.mapToPresenter(): MutableList<PCountry> {

    var mPCountryList = mutableListOf<PCountry>()

    this.forEach { mDCountry ->
        mPCountryList.add(mDCountry.mapToPresenter())

    }

    return mPCountryList
}


fun DCountry.mapToPresenter(): PCountry =
    PCountry(code, name, dial_code)

