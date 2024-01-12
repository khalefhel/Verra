package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import fr.raouf.verra.fragments.HomeFragment
import fr.raouf.verra.fragments.RegisterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connect = findViewById<Button>(R.id.connect)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val error = findViewById<TextView>(R.id.error)

        connect.setOnClickListener {
            error.visibility = View.GONE
            // Toast.makeText(this, "Je suis le bouton connect !", Toast.LENGTH_LONG).show()

            val txtEmail = email.text.toString()
            val txtPassword = password.text.toString()
            if (txtEmail.trim().isEmpty() || txtPassword.trim().isEmpty()) {
                error.text = "Vous devez remplir tout les champs"
                error.visibility = View.VISIBLE
                // Toast.makeText(this, "Vous devez remplir tous les champs!", Toast.LENGTH_LONG).show()
            } else {
                val correctEmail = "bsd.raouf1@gmail.com"
                val correctPassword = "a"

                if (correctEmail == txtEmail && correctPassword == txtPassword) {
                    email.setText("")
                    password.setText("")
                    // Intent explicite
                    Intent(this, HomeActivity::class.java).also {
                        it.putExtra("email", "Raouf")
                        startActivity(it)
                    }

                    error.text = "Bravo !"
                    error.visibility = View.VISIBLE
                } else {
                    error.text = "Email ou password n'est pas correct"
                    error.visibility = View.VISIBLE
                }
            }
        }

        val button_register: View = findViewById(R.id.button_register)
        button_register.setOnClickListener {
            val intent = Intent(this, RegisterFragment::class.java)
            startActivity(intent)
        }

        val button_tst: View = findViewById(R.id.button_tst)
        button_tst.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
    }
}