package sl.com.eightdigitz.di

import sl.com.eightdigitz.apiclient.BuildConfig
import sl.com.eightdigitz.client.apis.*
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.network.createNetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val tokenAuthenticator = SupportInterceptor()

private val retrofit: Retrofit =
    createNetworkClient(BuildConfig.API_URL, BuildConfig.DEBUG, tokenAuthenticator)

/**
 * All the APIs should initialise here to avoid Koin DfinitionOverrideException runtime error
 *
 */
val apiModule: Module = module {
    single { tokenAuthenticator }
    single { authApi }
    single { profileApi }
}

private val authApi: AuthApi = retrofit.create(AuthApi::class.java)

private val profileApi: ProfileApi = retrofit.create(ProfileApi::class.java)