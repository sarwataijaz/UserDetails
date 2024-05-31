package com.example.user_details.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.user_details.DataBase
import com.example.user_details.StoreDetails

// provides encapsulation

class UserViewModel(application: Application): ViewModel() {

    private val _user = MutableLiveData<StoreDetails>()
    val user: LiveData<StoreDetails> = _user

    private val saveData: DataBase = DataBase(application)

    fun submitDetails(id: Int, name: String, pass: String, acNo: Int, cash: Int) {
        //Here goes logic after button clicked.
        _user.value = StoreDetails(id,name,pass,acNo,cash)
    }

    fun addAccount(userName: String, password: String, accNo: Int, cash: Int) {
        saveData.addAccount(userName, password, accNo, cash)
    }

    fun getDetails(): List<StoreDetails>  {
        val recordsList = saveData.getDetails()
        return recordsList
    }

    fun deleteRecord(id: Int) : Boolean {
        return saveData.deleteRecord(id)
    }
}