package com.maronworks.postapplication.model

data class PostModel(
    val userCreated: String,
    val label: String,
    val datePosted: String
)

val postModelItems = listOf(
    PostModel(
        userCreated = "ralphmaron",
        label = "Ralph Maron Eda is a computer engineering student who is passionate about learning" +
                "different languages and technologies.",
        datePosted = "2024-03-02 at 2:04AM"
    ),
    PostModel(
        userCreated = "hello world",
        label = "Hello there, always be happy ok!",
        datePosted = "2024-03-03 at 10:43AM"
    )
)