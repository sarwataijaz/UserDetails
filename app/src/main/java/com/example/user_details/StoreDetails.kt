package com.example.user_details

class StoreDetails(val name: String, val pass: String, val acNo: Int, val cashh: Int){

     var userName: String
     var password: String
     var accNo: Int
     var cash: Int

    init {
        userName = name
        password = pass
        accNo = acNo
        cash = cashh
    }

    fun getUserName(): String {
        return userName
    }
    fun getPassword(): String {
        return password
    }
    fun getAccountNo(): Int {
        return accNo
    }
    fun getCash(): Int {
        return cash
    }
}