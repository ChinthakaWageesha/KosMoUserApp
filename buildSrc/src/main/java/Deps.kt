
object ApplicationId {
    const val id = "sl.com.eightdigitz.userapp"
}

object Modules {
    const val core = ":core"
    const val app = ":app"
    const val navigation = ":navigation"

    const val presentation = ":common:presentation"
    const val api = ":common:api"
    const val network = ":common:network"
    const val apiclient = ":apiclient"
    const val country_picker = ":country_picker"

    const val authentication = ":features:authentication"
    const val notifications = ":features:notifications"
    const val chat = ":features:chat"
}

object Auth0 {
    const val auth0Domain = "dev-ahamad.us.auth0.com"
    const val auth0Schema = "demo"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29

    const val gradle = "3.5.3"

    const val kotlin = "1.3.72"
    const val ktx = "1.0.0-alpha1"
    const val koin = "2.0.1"
    const val rxjava = "2.2.6"
    const val rxkotlin = "2.3.0"
    const val retrofit = "2.5.0"
    const val loggingInterceptor = "3.12.1"
    const val retrofitJson = "2.4.0"

    const val sdkVersion = "28.0.0"
    const val appcompat = "1.1.0"
    const val design = "1.2.0-beta01"
    const val cardview = "1.0.0"
    const val recyclerview = "1.0.0"
    const val constraintLayout = "2.0.4"
    const val maps = "15.0.1"
    const val lifecycle = "2.0.0"
    const val support = "1.0.0"

    const val playCore = "1.3.7"
    const val googleAuth = "16.0.1"
    const val googleServices = "4.2.0"

    const val room = "2.0.0"

    const val firebaseAuth = "17.2.1"
    const val firebaseCore = "17.2.1"
    const val firebaseMessaging = "20.1.0"
    const val firebaseCrashlytics = "2.10.1"
    const val firebaseAnalytics = "17.2.2"
    const val firebase = "19.1.0"


    const val timber = "4.7.1"
    const val glide = "4.9.0"

    const val volley = "1.1.0"
    const val gson = "2.8.5"
    const val okhttp = "3.10.0"

    const val butterKnife = "10.1.0"
    const val anko = "0.10.8"

    const val moshi = "1.8.0"
    const val threetenabp = "1.1.1"
    const val permission = "4.6.0"
    const val auth0 = "1.30.0"
    const val circularImageView = "3.1.0"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    const val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val volley = "com.android.volley:volley:${Versions.volley}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggin = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val butterKnife = "com.jakewharton:butterknife:${Versions.butterKnife}"
    const val butterKnifeCompiler = "com.jakewharton:butterknife-compiler:${Versions.butterKnife}"


    //  https://github.com/Kotlin/anko
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"

    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinExt ="org.koin:koin-android-ext:${Versions.koin}"

    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    const val retrofitJsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitJson}"

    const val threetenabp = "com.jakewharton.threetenabp:threetenabp:${Versions.threetenabp}"
}

object SupportLibraries {
    const val auth0 = "com.auth0.android:auth0:${Versions.auth0}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}}"
}

object ExternalLib {
    const val loader = "com.github.ybq:Android-SpinKit:1.4.0"
    const val imagePicker =  "com.github.dhaval2404:imagepicker:1.3"
    const val pictureSelector  = "com.github.LuckSiege.PictureSelector:picture_library:v2.2.4"

    const val permissionDispatcher =
        "org.permissionsdispatcher:permissionsdispatcher:${Versions.permission}"
    const val permissionDispatcherProcessor =
        "org.permissionsdispatcher:permissionsdispatcher-processor:${Versions.permission}"

    const val emoji = "com.vanniktech:emoji-google:0.6.0"
    const val filePicker = "com.droidninja:filepicker:2.2.1"
    const val imageViewer = "com.github.stfalcon:stfalcon-imageviewer:0.1.0"

    const val cropper = "com.isseiaoki:simplecropview:1.1.8"
    const val circularImageView = "de.hdodenhof:circleimageview:${Versions.circularImageView}"
    const val countryPick = "com.hbb20:ccp:2.4.0"
    const val eventCalendar = "com.github.mahimrocky:EventCalender:v1.0.0"
    const val switchButton = "com.github.zcweng:switch-button:0.0.3"
    const val videoPlayer =  "com.github.HamidrezaAmz:MagicalExoPlayer:1.0.16"
    const val roundCornerProgressBar = "com.akexorcist:round-corner-progress-bar:2.1.1"
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:2.7.2"

}

object GoogleLibraries {
    const val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    const val playCore = "com.google.android.play:core:${Versions.playCore}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object FirebaseLibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    val core = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    val messaging = "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"
    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.firebaseCrashlytics}"
    val analytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
    val database = "com.google.firebase:firebase-database:${Versions.firebase}"
    val database_ui = "com.firebaseui:firebase-ui-database:6.0.2"
}

object ApiLiveServer {
    const val url = "\"https://hello-api.dev.hello.eightdigitz.com/\""
}

object ApiSandboxServer {
    const val url = "\"https://hello-api.dev.hello.eightdigitz.com/\""
}

object Moshi {
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiAdapter = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
}

object AndroidX {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.support}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object Koin {
    const val android = "org.koin:koin-android:${Versions.koin}"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val ext = "org.koin:koin-android-ext:${Versions.koin}"
}

object Glide {
    const val runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}