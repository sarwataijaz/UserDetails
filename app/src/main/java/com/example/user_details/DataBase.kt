package com.example.user_details

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    companion object {
        // Define constants for database name and version
        // below variable is for our database name.
        private val DB_NAME = "PersonBankDetails"

        // below int is our database version
        private val DB_VERSION = 1

        // below variable is for our table name.
        private val TABLE_NAME = "AccDetails"

        // below variable is for our id column.
        private val ID_COL = "id"

        // below variable is for our course name column
        private val NAME_COL = "Username"

        // below variable id for our course duration column.
        private val PASSWORD_COL = "Password"

        // below variable for our course description column.
        private val ACCOUNT_COL = "AccountNumber"

        // below variable is for our course tracks column.
        private val CASH_COL = "Amount"

        // pass the details of the database
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ( $ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_COL TEXT, $PASSWORD_COL TEXT, $ACCOUNT_COL INTEGER, $CASH_COL INTEGER) "

        db?.execSQL(query)
    }

     fun addAccount(userName: String, password: String, accNo: Int, cash: Int) {

        val db : SQLiteDatabase = this.writableDatabase

        val values = ContentValues() // use to store key value pairs

        values.put(NAME_COL,userName)
        values.put(PASSWORD_COL,password)
        values.put(ACCOUNT_COL,accNo)
        values.put(CASH_COL,cash)

        db.insert(TABLE_NAME,null,values)

        db.close()
    }

     fun getDetails() : ArrayList<StoreDetails> {
        val db: SQLiteDatabase = this.writableDatabase

        val cursor = db.rawQuery("SELECT* FROM $TABLE_NAME", null)
        val arrayList = ArrayList<StoreDetails>()

        if(cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                arrayList.add(
                    StoreDetails(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                    )
                )
            } while (cursor.moveToNext())
            // moving our cursor to next.

        }

        cursor.close()
        return arrayList
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}