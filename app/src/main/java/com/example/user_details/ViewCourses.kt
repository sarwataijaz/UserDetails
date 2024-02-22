package com.example.user_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ViewCourses : AppCompatActivity() {

    private lateinit var mainLayout: LinearLayout
    private lateinit var data1: TextView
    private lateinit var cross: ImageView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        mainLayout = findViewById(R.id.mainLayout)
        data1 = findViewById(R.id.data1)

        val db = DataBase(this)
        val records = db.getDetails()

        for (record in records) {
            val textView = TextView(this)
            textView.text = "Customer number: ${record.getID()}\n" +
                    "UserName: ${record.getUserName()}\n" +
                    "Password: ${record.getPassword()}\n" +
                    "AccountNo: ${record.getAccountNo()}\n" +
                    "Cash: ${record.getCash()}\n"
            val image = ImageView(this)
            image.setImageResource(android.R.drawable.btn_dialog)

            image.setOnClickListener {
                val isDeleted = db.deleteRecord(record.getID())

                if(isDeleted) {
                    mainLayout.removeView(textView)
                    mainLayout.removeView(image)
                    Toast.makeText(this, "Record removed successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error removing the record from database", Toast.LENGTH_SHORT).show()
                }

            }
            mainLayout.addView(textView)
            mainLayout.addView(image)
        }
    }
}