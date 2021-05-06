package sl.com.eightdigitz.app.domain.repository

import io.reactivex.Single
import sl.com.eightdigitz.core.model.domain.DPromoCode

interface PaymentRepository {

    fun getOffersByPromoCode(promoCode: String): Single<DPromoCode>
}