package com.example.user_details

class StoreDetails(val id: Int,val name: String, val pass: String, val acNo: Int, val cashh: Int){

     var userID: Int
     var _userName: String
     var _password: String
     var _accNo: Int
     var _cash: Int

    init {
         userID = id
        _userName = name
        _password = pass
        _accNo = acNo
        _cash = cashh
    }

    fun getUserID(): Int {
        return userID
    }

    fun getUserName(): String {
        return _userName
    }
    fun getPassword(): String {
        return _password
    }
    fun getAccountNo(): Int {
        return _accNo
    }
    fun getCash(): Int {
        return _cash
    }
}