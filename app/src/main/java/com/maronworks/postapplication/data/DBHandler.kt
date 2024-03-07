package com.maronworks.postapplication.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.maronworks.postapplication.model.PostModel

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "app_db"
        const val DB_VERSION = 4
        const val USERS_TABLE = "users_table"
        const val USERNAME_COL = "username_col"
        const val PASSWORD_COL = "password_col"

        const val POSTS_TABLE = "posts_table"
        const val USER_CREATED_COL = "user_created_col"
        const val DATE_ADDED_COL = "date_added_col"
        const val LABEL_COL = "label_col" // post content

        const val CURRENT_USER_TABLE = "current_user_table"

        // creating tables
        const val CREATE_USERS_TABLE =
            "CREATE TABLE $USERS_TABLE ($USERNAME_COL TEXT NOT NULL, $PASSWORD_COL TEXT NOT NULL)"
        const val CREATE_POSTS_TABLE =
            "CREATE TABLE $POSTS_TABLE (id INTEGER PRIMARY KEY AUTOINCREMENT, $USER_CREATED_COL TEXT NOT NULL, $LABEL_COL TEXT NOT NULL, $DATE_ADDED_COL TEXT NOT NULL)"

        // for saving current user
        const val CREATE_CURRENT_USER_TABLE =
            "CREATE TABLE $CURRENT_USER_TABLE ($USERNAME_COL TEXT NOT NULL)"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USERS_TABLE)
        db?.execSQL(CREATE_POSTS_TABLE)
        db?.execSQL(CREATE_CURRENT_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $POSTS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $CURRENT_USER_TABLE")

        onCreate(db)
    }

    fun addNewUser(username: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USERNAME_COL, username)
        values.put(PASSWORD_COL, password)

        db.insert(USERS_TABLE, null, values)
        db.close()
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

    fun isUsernameInDB(username: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $USERS_TABLE WHERE $USERNAME_COL = ?",
            arrayOf(username)
        )
        val exists = cursor.count > 0

        cursor.close()
        return exists
    }

    fun savePost(
        userCreated: String,
        label: String,
        datePosted: String
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USER_CREATED_COL, userCreated)
        values.put(LABEL_COL, label)
        values.put(DATE_ADDED_COL, datePosted)

        db.insert(POSTS_TABLE, null, values)
        db.close()
    }

    fun readPosts(): List<PostModel> {
        val items = mutableListOf<PostModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $POSTS_TABLE", null)

        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    items.add(
                        PostModel(
                            id = cursor.getInt(0),
                            userCreated = cursor.getString(1),
                            label = cursor.getString(2),
                            datePosted = cursor.getString(3)
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        return items
    }

    fun updatePost(id: Int, username: String, label: String) {
        // update
    }

    fun deletePost(id: Int, username: String) {
        val db = this.readableDatabase

        db.delete(POSTS_TABLE, "id = ? AND $USER_CREATED_COL = ?", arrayOf(id.toString(), username))
    }

    fun setCurrentUser(currentUser: String) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USERNAME_COL, currentUser)

        db.insert(CURRENT_USER_TABLE, null, values)
        db.close()
    }

    @SuppressLint("Recycle")
    fun readCurrentUser(): String {
        val items: ArrayList<String> = ArrayList()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $CURRENT_USER_TABLE", null)

        if (cursor.moveToFirst()) {
            do {
                items.add(
                    cursor.getString(0)
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items[items.size - 1]
    }

    fun deleteAllCurrentUser() {
        val db = this@DBHandler.writableDatabase

        db.delete(CURRENT_USER_TABLE, null, null)
    }

    fun readPostWhereUsername(username: String): List<PostModel> {
        val items = mutableListOf<PostModel>()
        val db = this.readableDatabase
        val cursor =
            db.rawQuery("SELECT * FROM $POSTS_TABLE WHERE $USER_CREATED_COL = ?", arrayOf(username))

        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    items.add(
                        PostModel(
                            id = cursor.getInt(0),
                            userCreated = cursor.getString(1),
                            label = cursor.getString(2),
                            datePosted = cursor.getString(3)
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        return items
    }
}