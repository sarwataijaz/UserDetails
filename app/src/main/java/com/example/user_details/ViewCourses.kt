package com.example.user_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("NAME_SHADOWING")
class ViewCourses : AppCompatActivity() {

    private lateinit var mainLayout: LinearLayout
    private lateinit var data1: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_data)

        val db = DataBase(this)
        val records = db.getDetails()

        var textView = data1
        for(i in records) {

            val textView = TextView(this)
            textView.text = "UserName: ${i.getUserName()}\n" +
                    "Password: ${i.getPassword()}\n" +
                    "AccountNo: ${i.getAccountNo()}\n" +
                    "Cash: ${i.getCash()}\n"

            mainLayout.addView(textView)
        }
    }
}