package com.hanvir.uas

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        auth = FirebaseAuth.getInstance()

        val buttondaftar = findViewById<TextView>(R.id.daftarbaru)
        buttondaftar.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        val buttonforgot = findViewById<TextView>(R.id.textinputforgot)
        buttonforgot.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgotActivity::class.java)
            startActivity(intent)
            finish()
        }

        val buttonlogin = findViewById<Button>(R.id.cirLoginButton)
        buttonlogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextEmailLogin).text.toString().trim { it <= ' ' }
            val password = findViewById<EditText>(R.id.editTextPasswordLogin).text.toString().trim { it <= ' ' }
            auth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@LoginActivity) { task ->
                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(applicationContext, "Masukkan alamat email !", Toast.LENGTH_SHORT).show()
                        }else if(password.length < 6){
                            Toast.makeText(applicationContext, "Password harus lebih dari 6 karakter !", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(applicationContext, "Login gagal !", Toast.LENGTH_SHORT).show()
                            try {
                                throw task.exception!!
                            } catch (e: Exception) {
                                e.message?.let { it1 -> Log.e(ContentValues.TAG, it1) }
                            }
                        }
                    } else {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
        }
    }
}