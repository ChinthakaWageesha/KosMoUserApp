package sl.com.eightdigitz.navigation.features

import android.content.Intent
import sl.com.eightdigitz.navigation.loadIntentOrNull

object MainNavigation : DynamicFeature<Intent> {

    private const val MAIN = "sl.com.eightdigitz.app.presentation.main.MainActivity"

    override val dynamicStart: Intent?
        get() = MAIN.loadIntentOrNull()
}
