package com.hanvir.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val back = findViewById<Button>(R.id.backbtn)
        back.setOnClickListener {
            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val intent = intent

        val name = intent.getStringExtra("name").toString()
        val nametext = findViewById<TextView>(R.id.isinyanama)
        nametext.text = name

        val status = intent.getStringExtra("status").toString()
        val statustext = findViewById<TextView>(R.id.isinyastatus)
        statustext.text = status

        val imgurl = intent.getStringExtra("imgUrl").toString()
        val frame = findViewById<ImageView>(R.id.fotonya)
        Picasso.get().load(imgurl).into(frame)
    }
}