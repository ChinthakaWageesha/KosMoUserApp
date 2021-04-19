package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.OrderRepository
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateOrderStatusRequest

class OrderUseCase(private val orderRepository: OrderRepository) {

    fun getTalentById(talentId: String) =
        orderRepository.getTalentById(talentId)

    fun placeNewOrder(newOrderRequest: NewOrderRequest) =
        orderRepository.placeNewOrder(newOrderRequest)

    fun getOrdersByStages(stages: String) =
        orderRepository.getOrdersByStage(stages)

    fun updateOrderStatus(request: UpdateOrderStatusRequest) =
        orderRepository.updateOrderStatus(request)

    fun getTalentRates(talentId: String) =
        orderRepository.getTalentRate(talentId)

}