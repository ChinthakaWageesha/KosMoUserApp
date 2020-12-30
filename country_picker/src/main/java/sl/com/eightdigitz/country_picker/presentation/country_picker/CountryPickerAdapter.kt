package sl.com.eightdigitz.country_picker.presentation.country_picker

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import sl.com.eightdigitz.country_picker.presentation.country_picker.CountryPickerAdapter.CountryViewHolder
import sl.com.eightdigitz.country_picker.presentation.models.PCountry
import sl.com.eightdigitz.country_picker.utils.toCapitalizeFirstLetter
import kotlinx.android.synthetic.main.item_country_row.view.*
import sl.com.eightdigitz.country_picker.R
import java.util.*


class CountryPickerAdapter(
    private val mContext: Context,
    val mUserCountryString: String?,
    val mVisibleCountryCountListener: VisibleCountryCountListener
) :
    RecyclerView.Adapter<CountryViewHolder>(), Filterable {


    private lateinit var mCountryList: MutableList<PCountry>
    private lateinit var mOriginCountryList: MutableList<PCountry>
    private lateinit var mCountrySelectionListener: CountrySelectionListener
    private var mUserCountryIndex: Int = -1
    private var mQueryString: String = ""

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(mContext)
                .inflate(
                    R.layout.item_country_row,
                    parent,
                    false
                )
        )
    }


    fun setOnCountrySelectListener(mCountrySelectionListener: CountrySelectionListener) {
        this.mCountrySelectionListener = mCountrySelectionListener
    }

    fun setCountryItems(mCountryList: MutableList<PCountry>) {
        this.mCountryList = mCountryList
        this.mOriginCountryList = mCountryList
        getCountryPosition()
    }

    private fun getCountryPosition() {
        mUserCountryIndex =
            mOriginCountryList.indexOfFirst { mPCountry ->
                mPCountry.name.toString() == mUserCountryString.toString()
                    .toLowerCase(Locale.ENGLISH)
            }

    }


    override fun getItemCount(): Int = mCountryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.run {
            onBind(position)
        }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                mQueryString = charSequence.toString()
                mCountryList = if (mQueryString.isEmpty()) {
                    mOriginCountryList
                } else {
                    val mFilteredLists = mOriginCountryList.filter {
                        (it.name?.toLowerCase(Locale.ENGLISH)?.startsWith(
                            mQueryString.toLowerCase(
                                Locale.ENGLISH
                            )
                        )) as Boolean
                    }
                    mFilteredLists.toMutableList()

                }

                val mFilterResults = FilterResults()
                mFilterResults.values = mCountryList
                return mFilterResults
            }

            override fun convertResultToString(resultValue: Any?): CharSequence {
                return super.convertResultToString(resultValue)

            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                notifyDataSetChanged()
                mVisibleCountryCountListener.onCountryCountChange(mCountryList.size)
            }

        }


    }


    inner class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(position: Int) {
            val mCountry = mCountryList[position]

            itemView.text_country_name.text = mCountry.name
            itemView.text_code.text = mCountry.dialCode
            val flagCode = mCountry.code?.toLowerCase(Locale.ENGLISH)
            val drawableName = "flag_$flagCode"
            itemView.image_country_flag.setImageDrawable(
                ContextCompat.getDrawable(
                    mContext,
                    getResId(drawableName)
                )
            )

            itemView.text_header.visibility = View.VISIBLE
            if (position == 0) {
                mCountry.name?.let {
                    if (mUserCountryString != null) {

                        if (it.toLowerCase(Locale.ENGLISH) == mUserCountryString.toLowerCase(Locale.ENGLISH)) {
                            itemView.text_header.text =
                                mContext.getString(R.string.label_current_location)
                                    .toCapitalizeFirstLetter()
                            setCurrentHeaderStyle()
                        } else
                            itemView.text_header.text =
                                mCountry.name?.first()?.toUpperCase().toString()

                    } else {
                        itemView.text_header.text = mCountry.name?.first()?.toUpperCase().toString()
                    }

                }

            } else {
                if (mCountryList[position - 1].name != null && mCountry.name != null) {

                    if (mCountry.name?.toLowerCase(Locale.ENGLISH)?.first().toString() !=
                        mCountryList[position - 1].name?.toLowerCase(Locale.ENGLISH)?.first()
                            .toString()
                    ) {
                        itemView.text_header.text = mCountry.name?.first()?.toUpperCase().toString()

                    } else
                        itemView.text_header.visibility = View.GONE
                } else
                    itemView.text_header.visibility = View.GONE
            }

            if (position == 1) {
                if (mCountry.name?.toLowerCase(Locale.ENGLISH)?.first().toString() ==
                    mUserCountryString?.toLowerCase(Locale.ENGLISH)?.first().toString()
                ) {
                    mUserCountryString?.let {
                        if (it.toLowerCase().contains(mQueryString.toLowerCase())) {
                            itemView.text_header.visibility = View.VISIBLE
                            itemView.text_header.text =
                                mCountry.name?.first()?.toUpperCase().toString()
                        }
                    }
                }
            }



            itemView.card_country_row.setOnClickListener {
                mCountrySelectionListener.onCountrySelection(mCountry)
            }


        }

        private fun setCurrentHeaderStyle() {
            itemView.text_header.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorTransparent
                )
            )
        }


        private fun getResId(drawableName: String): Int {

            try {
                val res = R.drawable::class.java
                val field = res.getField(drawableName)
                return field.getInt(null)
            } catch (e: Exception) {
                Log.e("CountryCodePicker", "Failure to get drawable id.", e)
            }

            return -1
        }

    }


    interface CountrySelectionListener {

        fun onCountrySelection(mCountry: PCountry)

    }

    interface VisibleCountryCountListener {

        fun onCountryCountChange(mCount: Int)

    }

}