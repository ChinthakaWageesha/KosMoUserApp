package sl.com.eightdigitz.network

import okhttp3.*

class SupportInterceptor() : Interceptor, Authenticator {

    var idToken: String? = null
    private var callback: AuthenticatorCallBack? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request.Builder = chain.request().newBuilder()

        request.addHeader("Content-Type", "application/json")
        request.addHeader("Accept", "application/json")

        if (!idToken.isNullOrBlank())
            request.addHeader("x-id-token", idToken!!)

        return chain.proceed(request.build())
    }

    override fun authenticate(route: Route?, response: Response): Request? {

        return if (response.code() != 200) {
            callback?.onUnAuthorizedResponse(response.code())
            null
        } else {
            response.request()
                .newBuilder()
                .build()
        }
    }

    fun setAuthCallBackListener(authenticatorCallBack: AuthenticatorCallBack) {
        callback = authenticatorCallBack
    }

    interface AuthenticatorCallBack {
        fun onUnAuthorizedResponse(responseCode: Int?)
    }
}
