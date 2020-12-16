package sl.com.eightdigitz.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        println("SPLASH SCREEN")
        Handler().postDelayed({
            setResult(RESULT_OK).also { finish() }
        }, 2000)
    }
}
