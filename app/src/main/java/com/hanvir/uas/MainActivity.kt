package com.hanvir.uas

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    var attendancelist: ListView? = null
    var dataModalArrayList: ArrayList<DataModal?>? = null
    var db: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        attendancelist = findViewById(R.id.idLVAttendees)
        dataModalArrayList = ArrayList()

        db = FirebaseFirestore.getInstance()

        loadDatainListview()
    }

    private fun loadDatainListview() {
        db!!.collection("Data").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val dataModal = d.toObject(DataModal::class.java)
                        dataModalArrayList!!.add(dataModal)
                    }
                    val adapter = StaffAdapter(this@MainActivity, dataModalArrayList)
                    attendancelist!!.adapter = adapter
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Database Kosong",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }.addOnFailureListener {
                Toast.makeText(this@MainActivity, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show()
            }
    }
}
