package com.example.user_details

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

//  You may find it helpful to create a companion class, known as a contract class, which explicitly specifies the layout of your schema in a systematic and self-documenting way.
//A contract class is a container for constants that define names for URIs, tables, and columns.
    companion object {
        // Define constants for database name and version
        // below variable is for our database name.
        private const val DB_NAME = "PersonBankDetails"

        // below int is our database version
        private const val DB_VERSION = 1

        // below variable is for our table name.
        private const val TABLE_NAME = "AccDetails"

        // below variable is for our id column.
        private const val ID_COL = "id"

        // below variable is for our course name column
        private const val NAME_COL = "Username"

        // below variable id for our course duration column.
        private const val PASSWORD_COL = "Password"

        // below variable for our course description column.
        private const val ACCOUNT_COL = "AccountNumber"

        // below variable is for our course tracks column.
        private const val CASH_COL = "Amount"

        // pass the details of the database
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query =
            "CREATE TABLE $TABLE_NAME ( $ID_COL INTEGER PRIMARY KEY, $NAME_COL TEXT, $PASSWORD_COL TEXT, $ACCOUNT_COL INTEGER, $CASH_COL INTEGER) "

        db.execSQL(query)
    }

     fun addAccount (userName: String, password: String, accNo: Int?, cash: Int?) {

        val db : SQLiteDatabase = this.writableDatabase

        val values = ContentValues() // use to store key value pairs

        values.put(NAME_COL,userName)
        values.put(PASSWORD_COL,password)
        values.put(ACCOUNT_COL,accNo)
        values.put(CASH_COL,cash)

         // The second argument tells the framework what to do in the event that the ContentValues is empty (i.e., you did not put any values). If you specify the name of a column, the framework inserts a row and sets the value of that column to null. If you specify null, like in this code sample, the framework does not insert a row when there are no values.
        val newRowID = db.insert(TABLE_NAME,null,values)
        Log.d("Primary key:", newRowID.toString())
        db.close()
    }

     fun getDetails() : List<StoreDetails> {

         val recordsList = mutableListOf<StoreDetails>()

         val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

         if (cursor.moveToFirst()) {
             do {
                 val column1 = cursor.getInt(0)
                 val column2 = cursor.getString(1)
                 val column3 = cursor.getString(2)
                 val column4 = cursor.getInt(3)
                 val column5 = cursor.getInt(4)


                 // Create a DatabaseRecord object and add it to the list
                 val record = StoreDetails(column1, column2, column3, column4, column5)
                 recordsList.add(record)
             } while (cursor.moveToNext())
         }

        cursor.close()
        return recordsList
    }

    fun deleteRecord(primaryKey: Int) {
        val db = writableDatabase
        val primaryKeyValue = 1 // Example primary key value of the record you want to delete

// Delete the record with the specified primary key value
        val rowsAffected = db.delete(TABLE_NAME, "$NAME_COL = ?", arrayOf(primaryKeyValue.toString()))

        db.close()

// Check if the deletion was successful
        if (rowsAffected > 0) {
            // Deletion was successful
        } else {
            // No record was deleted (perhaps the record with the specified primary key value doesn't exist)
        }
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME"); // delete all
        onCreate(db);
    }



}