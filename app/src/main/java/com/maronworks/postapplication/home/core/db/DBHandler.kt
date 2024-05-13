package com.maronworks.postapplication.home.core.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.maronworks.postapplication.core.model.dbconfig.DBConfig
import com.maronworks.postapplication.home.core.domain.post.PostModel

class DBHandler(context: Context) :
    SQLiteOpenHelper(context, "posts_db2", null, 4) {
    companion object {
        private const val TABLE_POST = "posts"
        private const val COL_POST_ID = "post_id"
        private const val COL_USERNAME = "username"
        private const val COL_USER_IMAGE = "user_image"
        private const val COL_POST_CONTENT = "post_content"
        private const val COL_DATE_ADDED = "date_added"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_POST ($COL_POST_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_USERNAME TEXT NOT NULL, $COL_USER_IMAGE INT NOT NULL, $COL_POST_CONTENT TEXT NOT NULL, $COL_DATE_ADDED TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_POST")
    }

    fun createPost(post: PostModel) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(COL_USERNAME, post.username)
        values.put(COL_USER_IMAGE, post.userImage)
        values.put(COL_POST_CONTENT, post.postContent)
        values.put(COL_DATE_ADDED, post.dateAdded)

        db.insert(TABLE_POST, null, values)
    }

    fun readPosts(): MutableList<PostModel> {
        val posts = mutableListOf<PostModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_POST", null)

        cursor.let {
            if (cursor.moveToFirst()) {
                do {
                    posts.add(
                        PostModel(
                            id = cursor.getInt(0),
                            username = cursor.getString(1),
                            userImage = cursor.getInt(2),
                            postContent = cursor.getString(3),
                            dateAdded = cursor.getString(4)
                        )
                    )

                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        return posts
    }
}