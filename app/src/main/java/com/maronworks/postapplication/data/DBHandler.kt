package com.maronworks.postapplication.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "app_db"
        const val DB_VERSION = 1
        const val USERS_TABLE = "users_table"
        const val USERNAME_COL = "username_col"
        const val PASSWORD_COL = "password_col"

        const val USER_CREATED_COL = "user_created_col"
        const val DATE_ADDED_COL = "date_added_col"
        const val LABEL_COL = "label_col" // post content
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $USERS_TABLE ($USERNAME_COL TEXT, $PASSWORD_COL TEXT)"

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
    }

    fun addNewUser(username: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USERNAME_COL, username)
        values.put(PASSWORD_COL, password)

        db.insert(USERS_TABLE, null, values)
        db.close()
        // create new table [ex: username + '_table']
    }

    fun isUserExist(username: String, password: String):Boolean{
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $USERS_TABLE WHERE $USERNAME_COL = ? AND $PASSWORD_COL = ?", arrayOf(username, password))
        val exists = cursor.count > 0

        cursor.close()
        return exists
        // if exists is greater than 0, return true [go home] else stay
    }
}