package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.PaymentRepository

class PaymentUseCase(private val paymentRepository: PaymentRepository) {

    fun getOffersByPromoCode(promoCode: String) = paymentRepository.getOffersByPromoCode(promoCode)
}
