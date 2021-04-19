package sl.com.eightdigitz.app.datasource.remote

import android.content.SharedPreferences
import io.reactivex.Single
import sl.com.eightdigitz.app.data.datasource.OrderDataSource
import sl.com.eightdigitz.client.apiSupports.requests.NewOrderRequest
import sl.com.eightdigitz.client.apiSupports.requests.UpdateOrderStatusRequest
import sl.com.eightdigitz.client.apis.OrderApi
import sl.com.eightdigitz.core.model.ListResponse
import sl.com.eightdigitz.core.model.domain.DOrder
import sl.com.eightdigitz.core.model.domain.DTalentRate
import sl.com.eightdigitz.core.model.domain.DUpdateOrderStatus
import sl.com.eightdigitz.core.model.domain.DUser
import sl.com.eightdigitz.core.model.mapToDomain
import sl.com.eightdigitz.presentation.extensions.getIdToken

class OrderDataSourceImpl(
    private val mSharedPreferences: SharedPreferences,
    private val orderApi: OrderApi
) : OrderDataSource {

    override fun getTalentById(talentId: String): Single<DUser> =
        orderApi.getUserByID(
            userId = talentId
        ).map {
            it.data?.mapToDomain()
        }

    override fun placeNewOrder(newOrderRequest: NewOrderRequest): Single<DOrder> =
        orderApi.placeNewOrder(
            idToken = mSharedPreferences.getIdToken()!!,
            newOrderRequest = newOrderRequest
        ).map {
            it.data?.mapToDomain()
        }

    override fun getOrdersByStage(stages: String): Single<ListResponse<DOrder>> =
        orderApi.getOrdersByStage(
            idToken = mSharedPreferences.getIdToken()!!,
            orderStages = stages
        ).map {
            it.mapToDomain()
        }

    override fun updateOrderStatus(request: UpdateOrderStatusRequest): Single<DUpdateOrderStatus> =
        orderApi.updateUserOrderStatus(
            idToken = mSharedPreferences.getIdToken()!!,
            request = request
        ).map {
            it.data?.mapToDomain()
        }

    override fun getTalentRate(talentId: String): Single<DTalentRate> =
        orderApi.getTalentRates(
            idToken = mSharedPreferences.getIdToken()!!,
            talentID = talentId
        ).map {
            it.data?.mapToDomain()
        }
}