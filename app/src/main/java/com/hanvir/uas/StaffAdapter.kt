package com.hanvir.uas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso


class StaffAdapter
    (context: Context, dataModalArrayList: ArrayList<DataModal?>?) :
    ArrayAdapter<DataModal?>(context, 0, dataModalArrayList!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listitemView = convertView
        if (listitemView == null) {
            listitemView =
                LayoutInflater.from(context).inflate(R.layout.image_lv_item, parent, false)
        }
        val dataModal = getItem(position)
        val nameTV = listitemView!!.findViewById<TextView>(R.id.idTVtext)
        val attendance = listitemView.findViewById<ImageView>(R.id.idIVimage)
        nameTV.text = dataModal!!.name
        Picasso.get().load(dataModal.imgUrl).into(attendance)
        listitemView.setOnClickListener {
//            Toast.makeText(context, "Item clicked is : " + dataModal.name, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("name", dataModal.name)
            intent.putExtra("imgUrl", dataModal.imgUrl)
            intent.putExtra("status", dataModal.status)
            context.startActivity(intent)
        }
        return listitemView
    }
}