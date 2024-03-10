package com.maronworks.postapplication.core.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.maronworks.postapplication.core.model.user.UserModel
import com.maronworks.postapplication.mainf.domain.model.newpost.PostModel

open class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "app_db"
        private const val DB_VERSION = 8

        // auth
        private const val USERS_TABLE = "users_table"
        private const val USERNAME_COL = "username"
        private const val PASSWORD_COL = "password"
        private const val FULL_NAME_COL = "full_name"
        private const val USERS_QUERY = "CREATE TABLE $USERS_TABLE ($USERNAME_COL TEXT NOT NULL, " +
                "$PASSWORD_COL TEXT NOT NULL, $FULL_NAME_COL TEXT NOT NULL)"


        // core
        private const val CURRENT_USER_TABLE = "current_user_table"
        private const val CURRENT_USER_QUERY =
            "CREATE TABLE $CURRENT_USER_TABLE ($USERNAME_COL TEXT NOT NULL)"


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
        db?.execSQL(CURRENT_USER_QUERY)
//        db?.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $POST_TABLE")
        db?.execSQL("DROP TABLE IF EXISTS $CURRENT_USER_TABLE")
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
            "SELECT * FROM $USERS_TABLE WHERE $USERNAME_COL = ? AND $PASSWORD_COL = ?",
            arrayOf(username, password)
        )
        val exists = cursor.count > 0
        cursor.close()

        return exists
    }


    // core
    fun setCurrentUser(currentUser: String) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USERNAME_COL, currentUser)
        db.insert(CURRENT_USER_TABLE, null, values)
    }

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

    fun getCurrentUserDetails(username: String): UserModel {
        val items: ArrayList<UserModel> = ArrayList()
        val db = this.readableDatabase
        val cursor =
            db.rawQuery("SELECT * FROM $USERS_TABLE WHERE $USERNAME_COL = ?", arrayOf(username))

        if (cursor.moveToFirst()) {
            do {
                items.add(
                    UserModel(
                        fullName = cursor.getString(2),
//                        password = cursor.getString(1),
                        username = cursor.getString(0)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return items[items.size - 1]
    }

    fun deleteAllCurrentUser() {
        val db = this.writableDatabase

        db.delete(CURRENT_USER_TABLE, "", null)
    }


    // mainF
    fun addPost(
        userCreated: String,
        postContent: String,
        datePosted: String
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(USER_CREATED, userCreated)
        values.put(POST_CONTENT, postContent)
        values.put(DATE_POSTED, datePosted)

        db.insert(POST_TABLE, null, values)
    }

    fun readPost(): MutableList<PostModel> {
        val items = mutableListOf<PostModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $POST_TABLE", null)

        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    items.add(
                        PostModel(
                            id = cursor.getInt(0),
                            userCreated = cursor.getString(1),
                            postContent = cursor.getString(2),
                            datePosted = cursor.getString(3)
                        )
                    )
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        return items
    }

    fun deletePost(id: Int, userCreated: String) {
        val db = this.readableDatabase

        db.delete(
            POST_TABLE,
            "$POST_ID = ? AND $USER_CREATED = ?",
            arrayOf(id.toString(), userCreated)
        )
    }

    fun readPostWhereUser(username: String): MutableList<PostModel> {
        val items = mutableListOf<PostModel>()
        val db = this.readableDatabase
        val cursor =
            db.rawQuery("SELECT * FROM $POST_TABLE WHERE $USER_CREATED = ?", arrayOf(username))

        cursor?.let {
            if (cursor.moveToFirst()) {
                do {
                    items.add(
                        PostModel(
                            id = cursor.getInt(0),
                            userCreated = cursor.getString(1),
                            postContent = cursor.getString(2),
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