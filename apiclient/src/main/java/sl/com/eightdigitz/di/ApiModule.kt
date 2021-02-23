package sl.com.eightdigitz.di

import sl.com.eightdigitz.apiclient.BuildConfig
import sl.com.eightdigitz.client.apis.*
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.network.createNetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.math.sin

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
    single { preferencesApi }
    single { searchHistoryApi }
    single { joinContactApi }
    single { multimediaApi }
}

private val authApi: AuthApi = retrofit.create(AuthApi::class.java)

private val profileApi: ProfileApi = retrofit.create(ProfileApi::class.java)

private val preferencesApi: PreferencesApi = retrofit.create(PreferencesApi::class.java)

private val searchHistoryApi: SearchHistoryApi = retrofit.create(SearchHistoryApi::class.java)

private val joinContactApi: JoinContactApi = retrofit.create(JoinContactApi::class.java)

private val multimediaApi: MultimediaApi = retrofit.create(MultimediaApi::class.java)