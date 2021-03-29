package sl.com.eightdigitz.client.apis

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.client.apiSupports.responses.NewOrderResponse
import sl.com.eightdigitz.client.apiSupports.responses.OrderListResponse

@JvmSuppressWildcards
interface OrderApi {

    @POST("order")
    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    fun placeNewOrder(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Body newOrderRequest: NewOrderRequest
    ): Single<NewOrderResponse>

    @GET("order/list-by-stages")
    fun getOrdersByStage(
        @retrofit2.http.Header("x-id-token") idToken: String,
        @retrofit2.http.Query("stages") orderStages: String
    ): Single<OrderListResponse>
}