package com.maronworks.postapplication.core.local.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "app_db"
        private const val DB_VERSION = 1

        // constants for user table
        private const val USER_TABLE = "users"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
        private const val CREATE_USER_TABLE_QUERY =
            "CREATE TABLE $USER_TABLE ($USERNAME TEXT NOT NULL, $PASSWORD TEXT NOT NULL);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
        onCreate(db)
    }

    fun insertUser(
        username: String,
        password: String,
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USERNAME, username)
        values.put(PASSWORD, password)

        db.insert(USER_TABLE, null, values)
    }

    fun isUserExists(
        username: String,
        password: String,
    ): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $USER_TABLE WHERE $USERNAME = ? AND $PASSWORD = ?",
            arrayOf(username, password)
        )
        val exists = cursor.count > 0
        cursor.close()

        return exists
    }
}