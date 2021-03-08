package sl.com.eightdigitz.network

import org.json.JSONObject
import retrofit2.HttpException

object ErrorHandler {

    fun getApiErrorMessage(response: Throwable): NMessage {
        val message = NMessage()
         try {
            val mResponse: HttpException = (response as HttpException)


            if (mResponse.code() == 500){
                message.error =  "Sorry! Server under maintenance At the Moment. Please try again in a while."
            } else {
                val jsonObject =
                    JSONObject(String(mResponse.response().errorBody()?.bytes()!!))

                //message.error = jsonObject.getString("message")
                message.error = (jsonObject.get("message") as JSONObject).get("error").toString()


            }
            return message

        } catch (ex: Exception) {
            message.error = "Oops, something went wrong. Let\'s try it again."
            return message
        }
    }
}
