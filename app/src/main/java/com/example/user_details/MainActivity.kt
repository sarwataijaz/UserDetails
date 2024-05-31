package com.example.user_details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.widget.Toast
import com.example.user_details.databinding.ActivityMainBinding
import com.example.user_details.viewModel.UserViewModel

// : Annotations can provide compiler instructions or hints about how code should be processed. For example, annotations can influence how code is optimized, how warnings or errors are handled, or how code is generated.
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.submit.setOnClickListener {
            val userNameString = binding.userName.text.toString()
            val passwordString = binding.password.text.toString()
            val accNoInt = binding.accNo.text.toString().toIntOrNull()
            val cashInt = binding.cash.text.toString().toIntOrNull()

            if(userNameString.isEmpty() || passwordString.isEmpty() || accNoInt == null || cashInt == null) {
                Toast.makeText(this, "Please enter all data", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addAccount(userNameString, passwordString, accNoInt, cashInt)
                Toast.makeText(this, "Data submitted!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.view.setOnClickListener {
            Intent(this,ViewCoursesActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}