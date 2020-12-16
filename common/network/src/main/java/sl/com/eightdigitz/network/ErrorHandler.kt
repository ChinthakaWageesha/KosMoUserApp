package sl.com.eightdigitz.network

import org.json.JSONObject
import retrofit2.HttpException

object ErrorHandler {

    fun getApiErrorMessage(response: Throwable): String {

        return try {
            val mResponse: HttpException? = (response as HttpException)
            when (mResponse?.code()) {
                500 -> "Sorry! Service Unavailable At the Moment" // TODO change 500 msg accordingly

                else -> {
                    val jsonObject =
                        JSONObject(String(mResponse?.response()?.errorBody()?.bytes()!!))

                    jsonObject.getString("message")
                }
            }
        } catch (ex: Exception) {
            "Oops, something went wrong. Let\'s try it again."
        }
    }
}
