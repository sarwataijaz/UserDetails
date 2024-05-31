package com.example.user_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.user_details.databinding.ActivityMainBinding
import com.example.user_details.databinding.ActivityViewCoursesBinding
import com.example.user_details.viewModel.UserViewModel

class ViewCoursesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewCoursesBinding
    private val viewModel: UserViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_courses)

        val records = viewModel.getDetails()

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
                val isDeleted = viewModel.deleteRecord(record.getID())

                if(isDeleted) {
                    binding.mainLayout.removeView(textView)
                    binding.mainLayout.removeView(image)
                    Toast.makeText(this, "Record removed successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error removing the record from database", Toast.LENGTH_SHORT).show()
                }

            }
            binding.mainLayout.addView(textView)
            binding.mainLayout.addView(image)
        }
    }
}