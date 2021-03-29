package sl.com.eightdigitz.app.domain.usecase

import sl.com.eightdigitz.app.domain.repository.OrderRepository
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest

class OrderUseCase(private val orderRepository: OrderRepository) {

    fun placeNewOrder(newOrderRequest: NewOrderRequest) =
        orderRepository.placeNewOrder(newOrderRequest)

    fun getOrdersByStages(stages: String) =
        orderRepository.getOrdersByStage(stages)

}