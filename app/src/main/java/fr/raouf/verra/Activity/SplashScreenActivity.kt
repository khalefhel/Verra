package fr.raouf.verra.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.raouf.verra.R


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Rediriger vers la page principale "MainActivity" après ' secondes
        Handler(Looper.getMainLooper()).postDelayed({
            val auth = Firebase.auth
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Intent(this, HomeActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
            finish()
        }, 1000)
    }
}