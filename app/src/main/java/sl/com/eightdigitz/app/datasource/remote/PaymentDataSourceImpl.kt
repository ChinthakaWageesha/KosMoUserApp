package sl.com.eightdigitz.app.datasource.remote

import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.PaymentDataSource
import sl.com.eightdigitz.client.apis.PaymentsApi
import sl.com.eightdigitz.core.model.domain.DPromoCode
import sl.com.eightdigitz.core.model.mapToDomain

class PaymentDataSourceImpl(
    private val paymentsApi: PaymentsApi
) : PaymentDataSource {

    override fun getOffersByPromoCode(promoCode: String): Single<DPromoCode> =
        paymentsApi.getOfferByPromoCode(promoCode)
            .map {
                it.data?.mapToDomain()
            }
}