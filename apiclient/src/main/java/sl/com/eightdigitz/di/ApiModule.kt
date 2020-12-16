package sl.com.eightdigitz.di

import sl.com.eightdigitz.apiclient.BuildConfig
import sl.com.eightdigitz.client.apis.*
import sl.com.eightdigitz.network.SupportInterceptor
import sl.com.eightdigitz.network.createNetworkClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val tokenAuthenticator = SupportInterceptor(BuildConfig.API_KEY)

private val retrofit: Retrofit =
    createNetworkClient(BuildConfig.API_URL, BuildConfig.DEBUG, tokenAuthenticator)

/**
 * All the APIs should initialise here to avoid Koin DfinitionOverrideException runtime error
 *
 */
val apiModule: Module = module {
    single { tokenAuthenticator }

    single { authApi }
    single { forgotPasswordApi }
    single { profileApi }
    single { userApi }
    single { resetPasswordApi }
    single { chatApi }
}

// Authentication API
private val authApi: AuthApi = retrofit.create(AuthApi::class.java)

// Forgot Password API
private val forgotPasswordApi: ForgotPasswordApi = retrofit.create(ForgotPasswordApi::class.java)

private val profileApi: ProfileApi = retrofit.create(ProfileApi::class.java)

private val userApi: UserApi = retrofit.create(UserApi::class.java)

private val resetPasswordApi: ResetPasswordApi = retrofit.create(ResetPasswordApi::class.java)

private val chatApi: ChatApi = retrofit.create(ChatApi::class.java)
