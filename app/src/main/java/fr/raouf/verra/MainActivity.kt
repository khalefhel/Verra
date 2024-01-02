package fr.raouf.verra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.raouf.verra.fragments.LinkFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }
}