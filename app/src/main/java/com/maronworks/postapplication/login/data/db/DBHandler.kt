package com.maronworks.postapplication.login.data.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.maronworks.postapplication.core.model.dbconfig.DBConfig
import com.maronworks.postapplication.login.model.user.UserModel

class DBHandler(private val context: Context) :
    SQLiteOpenHelper(context, DBConfig.DB_NAME, null, DBConfig.DB_VERSION) {

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