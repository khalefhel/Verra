package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.raouf.verra.fragments.RegisterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val button_register: View = findViewById(R.id.button_register)
        button_register.setOnClickListener {
            val intent = Intent(this, RegisterFragment::class.java)
            startActivity(intent)
        }
    }
}