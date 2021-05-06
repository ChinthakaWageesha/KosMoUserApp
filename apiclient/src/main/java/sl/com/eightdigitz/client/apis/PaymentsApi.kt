package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import sl.com.eightdigitz.client.apiSupports.responses.PromoCodeDiscountResponse

@JvmSuppressWildcards
interface PaymentsApi {

    @GET("promo-code/discount")
    fun getOfferByPromoCode(
        @retrofit2.http.Query("promo-code") promoCode: String
    ): Single<PromoCodeDiscountResponse>
}