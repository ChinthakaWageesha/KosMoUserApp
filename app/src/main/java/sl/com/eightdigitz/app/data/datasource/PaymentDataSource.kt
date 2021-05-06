package sl.com.eightdigitz.app.data.datasource

import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DPromoCode

interface PaymentDataSource {

    fun getOffersByPromoCode(promoCode: String): Single<DPromoCode>
}