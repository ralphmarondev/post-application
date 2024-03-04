// old readposts
fun readPosts(): ArrayList<PostModel> {
    val items: ArrayList<PostModel> = ArrayList()
    val db = this.readableDatabase
    val cursor = db.rawQuery("SELECT * FROM $POSTS_TABLE", null)

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
    return items
}