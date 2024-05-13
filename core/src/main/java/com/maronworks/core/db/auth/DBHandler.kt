package com.maronworks.core.db.auth

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) :
    SQLiteOpenHelper(context, "db_users", null, 1) {

    companion object {
        const val TABLE_USER = "users"
        const val COL_USERNAME = "username"
        const val COL_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_USER ($COL_USERNAME TEXT NOT NULL, $COL_PASSWORD TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
    }

    fun createUser(user: UserModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_USERNAME, user.username)
        values.put(COL_PASSWORD, user.password)

        db.insert(TABLE_USER, null, values)
    }

    fun isUserExist(user: UserModel): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USER WHERE $COL_USERNAME = ? AND $COL_PASSWORD = ?",
            arrayOf(user.username, user.password)
        )
        val exists = cursor.count > 0
        cursor.close()

        return exists
    }
}