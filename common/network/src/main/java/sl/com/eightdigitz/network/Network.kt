package sl.com.eightdigitz.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

fun createNetworkClient(
    baseUrl: String,
    debug: Boolean = false,
    supportInterceptor: SupportInterceptor
) =
    retrofitClient(baseUrl, httpClient(debug, supportInterceptor))

private fun httpClient(
    debug: Boolean,
    supportInterceptor: SupportInterceptor
): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()

    if (debug) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }

//    TODO handle 401 and interact user to re-authenticate using Authenticator, see @SupportInterceptor fun authenticate()
    clientBuilder.authenticator(supportInterceptor)
    clientBuilder.addInterceptor(supportInterceptor)

    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
