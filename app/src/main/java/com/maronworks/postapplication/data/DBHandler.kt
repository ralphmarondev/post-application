package com.maronworks.postapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "app_db"
        const val DB_VERSION = 3
        const val USERS_TABLE = "users_table"
        const val USERNAME_COL = "username_col"
        const val PASSWORD_COL = "password_col"

        const val POSTS_TABLE = "posts_table"
        const val USER_CREATED_COL = "user_created_col"
        const val DATE_ADDED_COL = "date_added_col"
        const val LABEL_COL = "label_col" // post content

        const val CURRENT_USER = "current_user_table"

        // creating tables
        const val CREATE_USERS_TABLE =
            "CREATE TABLE $USERS_TABLE ($USERNAME_COL TEXT NOT NULL, $PASSWORD_COL TEXT NOT NULL)"
        const val CREATE_POSTS_TABLE =
            "CREATE TABLE $POSTS_TABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, $USER_CREATED_COL TEXT NOT NULL, $LABEL_COL TEXT NOT NULL, $DATE_ADDED_COL TEXT NOT NULL)"

        // for saving current user
        const val CREATE_CURRENT_USER_TABLE =
            "CREATE TABLE $CURRENT_USER ($USERNAME_COL TEXT NOT NULL)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USERS_TABLE)
        db?.execSQL(CREATE_POSTS_TABLE)
        db?.execSQL(CREATE_CURRENT_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $POSTS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $CURRENT_USER")

        onCreate(db)
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

    fun isUserExist(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $USERS_TABLE WHERE $USERNAME_COL = ? AND $PASSWORD_COL = ?",
            arrayOf(username, password)
        )
        val exists = cursor.count > 0

        cursor.close()
        return exists
        // if exists is greater than 0, return true [go home] else stay
    }

    // table for post
    fun createTable(username: String) {
        val db = this.writableDatabase

        db.execSQL("CREATE TABLE ${username}_table (id INTEGER PRIMARY KEY AUTOINCREMENT, $USER_CREATED_COL TEXT NOT NULL, $LABEL_COL TEXT NOT NULL, $DATE_ADDED_COL TEXT NOT NULL)")
        db.close()
    }

    fun savePost(
        userCreated: String,
        label: String,
        datePosted: String
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        //val table = userCreated + "_table"

        values.put(USER_CREATED_COL, userCreated)
        values.put(LABEL_COL, label)
        values.put(DATE_ADDED_COL, datePosted)

        db.insert(POSTS_TABLE, null, values)
        db.close()
    }

    fun readPosts() {
        // TODO: Implement this 
    }

    fun setCurrentUser(currentUser: String) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USERNAME_COL, currentUser)

        db.insert(CURRENT_USER, null, values)
        db.close()
    }

    @SuppressLint("Recycle")
    fun readCurrentUser(): String {
        val items: ArrayList<String> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $CURRENT_USER", null)

        if(cursor.moveToFirst()){
            do{
                items.add(
                    cursor.getString(0)
                )
            }while(cursor.moveToNext())
        }
        cursor.close()
        return items[items.size - 1]
    }
}