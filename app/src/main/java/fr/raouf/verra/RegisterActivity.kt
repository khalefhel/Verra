package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import fr.raouf.verra.data.User
import fr.raouf.verra.db.VerraDatabase

class RegisterActivity : AppCompatActivity() {
    lateinit var db : VerraDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        db = VerraDatabase(this)
        val editName = findViewById<EditText>(R.id.editName)
        val editEmail = findViewById<EditText>(R.id.editEmail)
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val rgError = findViewById<TextView>(R.id.rgError)

        btnSave.setOnClickListener {
            rgError.visibility = View.INVISIBLE
            rgError.text = ""

            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val phone = editPhone.text.toString()
            val password = editPassword.text.toString()

            // check if null
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                rgError.text = getString(R.string.error)
                rgError.visibility = View.VISIBLE
            } else {
                val user = User(name, email, phone, password)
                val isInserted = db.addUser(user)
                if (isInserted) {
                    Toast.makeText(this, getString(R.string.great), Toast.LENGTH_SHORT).show()
                    Intent(this, HomeActivity::class.java).also {
                        it.putExtra("email", email)
                        startActivity(it)
                    }
                }
            }
        }

        val button_link: View = findViewById(R.id.button_link)
        button_link.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}