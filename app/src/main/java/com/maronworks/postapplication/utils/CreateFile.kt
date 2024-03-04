package com.maronworks.postapplication.utils

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

private const val DEFAULT_FILE_NAME = "current_user.text"

fun createFileInAppDirectory(context: Context): Boolean {
    val file = File(getAppDirectoryApp(), DEFAULT_FILE_NAME)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        if (!file.exists()) {
            try {
                // attempt to create the file
                file.createNewFile()
            } catch (e: IOException) {
                // file creation failed
                e.printStackTrace()
            }
        }
        // file already exists
        createFileInScopedStorage(context)
    } else {
        createFileInLegacyStorage()
    }
}

fun writeToFile(content: String): Boolean {
    val filePath = getFilePath()
    val file = File(filePath)

    return try {
        Files.write(Paths.get(filePath), content.toByteArray(StandardCharsets.UTF_8))
        true // write operation successful
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun readFromFile(): String? {
    val filePath = getFilePath()
    val file = File(filePath)

    return try {
        String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

private fun getFilePath(): String {
    val directoryPath = getAppDirectoryApp()
    return "$directoryPath/$DEFAULT_FILE_NAME"
}

private fun getAppDirectoryApp(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath
    } else {
        "${Environment.getExternalStorageDirectory().absolutePath}/PostApp"
    }
}

// create new file in scoped storage (Android 10 and above)
private fun createFileInScopedStorage(context: Context): Boolean {
    val contentValue = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, DEFAULT_FILE_NAME)
        put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
        put(
            MediaStore.MediaColumns.RELATIVE_PATH,
            Environment.DIRECTORY_DOCUMENTS + "/PostApp"
        )
    }

    val uri =
        context.contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValue)
    return uri != null
}

// create a new file in legacy storage (Android 9 and below)
private fun createFileInLegacyStorage(): Boolean {
    val rootDirectory = File(Environment.getExternalStorageDirectory(), "PostApp")

    if (!rootDirectory.exists()) {
        rootDirectory.mkdirs()
    }

    val file = File(rootDirectory, com.maronworks.postapplication.utils.DEFAULT_FILE_NAME)
    return try {
        file.createNewFile()
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }
}
/*
sample usage
val fileName = "example.txt"

val success = createFileInAppDirectory(fileName)
if (success) {
    // File creation was successful
    // Perform additional operations
} else {
    // File creation failed
    // Display an error message to the user
}
*/
