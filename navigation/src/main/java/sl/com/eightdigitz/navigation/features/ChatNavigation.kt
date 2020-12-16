package sl.com.eightdigitz.navigation.features

import android.content.Intent
import sl.com.eightdigitz.navigation.loadIntentOrNull

object ChatNavigation : DynamicFeature<Intent> {

    private const val SPLASH = "sl.com.eightdigitz.chat.presentation.ui.chat.ChatActivity"

    override val dynamicStart: Intent?
        get() = SPLASH.loadIntentOrNull()
}
