package sl.com.eightdigitz.app.data.repository

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.PaymentDataSource
import sl.com.eightdigitz.app.domain.repository.PaymentRepository
import sl.com.eightdigitz.core.model.domain.DPromoCode

class PaymentRepositoryImpl constructor(
    private val paymentDataSource: PaymentDataSource
) : PaymentRepository {

    override fun getOffersByPromoCode(promoCode: String): Single<DPromoCode> =
        paymentDataSource.getOffersByPromoCode(promoCode)

}
