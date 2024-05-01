package com.maronworks.postapplication.home.data.local.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.maronworks.postapplication.home.domain.post.PostModel

class DBHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "posts_db"
        private const val DB_VERSION = 1

        // posts
        private const val POSTS_TABLE = "posts_table"
        private const val OWNER_PICTURE = "owner_picture"
        private const val OWNER_NAME = "owner_name"
        private const val POST_CONTENT = "post_content"
        private const val DATE_POSTED = "date_posted"
        private const val CREATE_POSTS_TABLE =
            "CREATE TABLE $POSTS_TABLE ($OWNER_PICTURE INTEGER NOT NULL," +
                    "$OWNER_NAME TEXT NOT NULL, $POST_CONTENT TEXT NOT NULL, $DATE_POSTED TEXT NOT NULL);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_POSTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $POSTS_TABLE")
    }

    fun createPost(
        post: PostModel,
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(OWNER_PICTURE, post.ownerPicture)
        values.put(OWNER_NAME, post.ownerName)
        values.put(POST_CONTENT, post.postContent)
        values.put(DATE_POSTED, post.datePosted)

        db.insert(POSTS_TABLE, null, values)
    }

    fun readPosts(): MutableList<PostModel> {
        val posts = mutableListOf<PostModel>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $POSTS_TABLE", null)

        cursor.let {
            if (cursor.moveToFirst()) {
                do {
                    posts.add(
                        PostModel(
                            ownerPicture = cursor.getInt(0),
                            ownerName = cursor.getString(1),
                            postContent = cursor.getString(2),
                            datePosted = cursor.getString(3)
                        )
                    )

                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        return posts
    }
}