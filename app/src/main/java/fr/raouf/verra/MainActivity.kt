package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.ktx.Firebase
import fr.raouf.verra.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var connect: Button
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var buttonregister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        connect = findViewById<Button>(R.id.connect)
        email = findViewById<EditText>(R.id.edit_Email)
        password = findViewById<EditText>(R.id.edit_Password)
        val error = findViewById<TextView>(R.id.error)
        buttonregister = findViewById<Button>(R.id.button_register)

        buttonregister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        val button_tst: View = findViewById(R.id.button_tst)
        button_tst.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        connect.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}