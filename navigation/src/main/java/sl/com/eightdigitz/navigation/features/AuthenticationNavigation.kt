package sl.com.eightdigitz.navigation.features

import android.content.Intent
import sl.com.eightdigitz.navigation.loadIntentOrNull

object AuthenticationNavigation : DynamicFeature<Intent> {

    private const val LOGIN = "sl.com.eightdigitz.authentication.presentation.ChooseLanguageAc"

    override val dynamicStart: Intent?
        get() = LOGIN.loadIntentOrNull()
}
