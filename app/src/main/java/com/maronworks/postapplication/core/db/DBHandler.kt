package com.maronworks.postapplication.core.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "app_db"
        private const val DB_VERSION = 7

        // auth
        private const val USERS_TABLE = "users_table"
        private const val USERNAME_COL = "username"
        private const val PASSWORD_COL = "password"
        private const val FULL_NAME_COL = "full_name"
        private const val USERS_QUERY = "CREATE TABLE $USERS_TABLE ($USERNAME_COL TEXT NOT NULL, " +
                "$PASSWORD_COL TEXT NOT NULL, $FULL_NAME_COL TEXT NOT NULL)"

        // mainF
        private const val POST_TABLE = "post_table"
        private const val POST_ID = "post_id"
        private const val USER_CREATED = "user_created"
        private const val POST_CONTENT = "post_content"
        private const val DATE_POSTED = "date_posted"
        private const val POST_QUERY =
            "CREATE TABLE $POST_TABLE ($POST_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$USER_CREATED TEXT NOT NULL, $POST_CONTENT TEXT NOT NULL, $DATE_POSTED TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(USERS_QUERY)
        db?.execSQL(POST_QUERY)
//        db?.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $POST_TABLE")
        onCreate(db)
    }

    // auth
    fun addUser(
        fullName: String,
        username: String,
        password: String
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(FULL_NAME_COL, fullName)
        values.put(USERNAME_COL, username)
        values.put(PASSWORD_COL, password)

        db.insert(USERS_TABLE, null, values)
//        db.close()
    }

    fun isUserExists(
        username: String,
        password: String
    ): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $USERS_TABLE WHERE $USERNAME_COL = ? AND $PASSWORD_COL = ?", arrayOf(username, password)
        )
        val exists = cursor.count > 0
        cursor.close()

        return exists
    }

    // mainF
    fun addPost(
        userCreated: String,
        postContent: String,
        datePosted: String
    ) {

    }

    fun readPost() {

    }

}