package fr.raouf.verra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvHello = findViewById<TextView>(R.id.tvHello)

        // 1: recuperer l'email envoyé par l'activityMain
        val email = intent.getStringExtra("email")

        // 2: Afficher l'email dans le tvHello
        tvHello.text = "Bienvenu : $email"
    }
}