package com.example.user_details

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// : Annotations can provide compiler instructions or hints about how code should be processed. For example, annotations can influence how code is optimized, how warnings or errors are handled, or how code is generated.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName: EditText = findViewById(R.id.userName)
        val password: EditText = findViewById(R.id.password)
        val accNo: EditText = findViewById(R.id.accNo)
        val cash: EditText =  findViewById(R.id.cash)

        val submit: Button = findViewById(R.id.submit)
        val view: Button = findViewById(R.id.view)

        val saveData = DataBase(this)
        submit.setOnClickListener {
            val userNameString = userName.text.toString()
            val passwordString = password.text.toString()
            val accNoInt = accNo.text.toString().toIntOrNull()
            val cashInt = cash.text.toString().toIntOrNull()

            saveData.addAccount(userNameString, passwordString, accNoInt, cashInt)

            Toast.makeText(this, "Data submitted!", Toast.LENGTH_SHORT).show()
        }

        view.setOnClickListener {
            Intent(this,ViewCourses::class.java).also {
                startActivity(it)
            }
        }
    }

}