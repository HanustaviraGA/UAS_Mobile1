package com.hanvir.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth

class ForgotActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        auth = FirebaseAuth.getInstance()

        val buttonlogin = findViewById<TextView>(R.id.gotaccforgot)
        buttonlogin.setOnClickListener {
            val intent = Intent(this@ForgotActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val pulihkan = findViewById<Button>(R.id.cirForgotButton)
        pulihkan.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextEmailForgot).text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(application, "Email tidak boleh kosong !", Toast.LENGTH_SHORT).show()
            }
            auth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@ForgotActivity,
                            "Instruksi pemulihan password terkirim !",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@ForgotActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@ForgotActivity,
                            "Gagal !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}