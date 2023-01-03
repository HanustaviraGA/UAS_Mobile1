package com.hanvir.uas

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    private lateinit var isdialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        auth = FirebaseAuth.getInstance()

        val buttonlogin = findViewById<TextView>(R.id.gotacc)
        buttonlogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val regbutton = findViewById<Button>(R.id.regbutton)
        regbutton.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailreg).text.toString().trim { it <= ' ' }
            val password = findViewById<EditText>(R.id.passwordreg).text.toString().trim { it <= ' ' }
            //create user
            auth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this@RegisterActivity) { task ->
                startLoading()
//                Toast.makeText(this@RegisterActivity,"createUserWithEmail:onComplete:" + task.isSuccessful, Toast.LENGTH_SHORT).show()
                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful) {
                    if(email.length < 6 || password.length < 6){
                        Toast.makeText(this@RegisterActivity, "Email dan password harus lebih dari 6." + task.exception,
                            Toast.LENGTH_SHORT).show()
                    }else if(email.isEmpty() || password.isEmpty()){
                        Toast.makeText(this@RegisterActivity, "Email dan password tidak boleh kosong." + task.exception,
                            Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@RegisterActivity, "Autentikasi gagal." + task.exception,
                            Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    isDismiss()
                    Toast.makeText(this@RegisterActivity, "Berhasil daftar.", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun startLoading(){
        /**set View*/
        val infalter = this.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item,null)
        /**set Dialog*/
        val bulider = AlertDialog.Builder(this)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.show()
    }
    private fun isDismiss(){
        isdialog.dismiss()
    }
}