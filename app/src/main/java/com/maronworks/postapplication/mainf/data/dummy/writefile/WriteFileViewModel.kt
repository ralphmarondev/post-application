package com.maronworks.postapplication.mainf.data.dummy.writefile

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel

class WriteFileViewModel : ViewModel() {
    // region ICONS
    private var comment = mutableStateOf(false)
    private var favorite = mutableStateOf(false)
    private var repost = mutableStateOf(false)
    private var share = mutableStateOf(false)
    fun onCommentClick() {
        comment.value = !comment.value
    }

    fun onFavoriteClick() {
        favorite.value = !favorite.value
    }

    fun onRepostClick() {
        repost.value = !repost.value
    }

    fun onShareClick() {
        share.value = !share.value
    }

    fun commentIcon(): ImageVector {
        return if (comment.value) Icons.Filled.Email else Icons.Outlined.Email
    }

    fun favoriteIcon(): ImageVector {
        return if (favorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    }

    fun repostIcon(): ImageVector {
        return if (repost.value) Icons.Filled.AccountBox else Icons.Outlined.AccountBox
    }

    fun shareIcon(): ImageVector {
        return if (share.value) Icons.Filled.Share else Icons.Outlined.Share
    }
    // endregion ICONS

//    private val _fileCreationResult = MutableLiveData<Uri?>()
//    val fileCreationResult: LiveData<Uri?> = _fileCreationResult
//
//    fun createFileWithSAF(activity: Activity){
//        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
//            addCategory(Intent.CATEGORY_OPENABLE)
//            type = "text/plain"
//            putExtra(Intent.EXTRA_TITLE, "example.txt")
//        }
//    }
//
//    private var createFileLauncher: ActivityResultLauncher<Intent>? = null
//    private var _fileCreationCallback: ((Uri?) -> Unit)? = null
//
//    fun setupActivityResultLauncher(createFileCallback: (Uri?) -> Unit, activity: Activity) {
//        _fileCreationCallback = createFileCallback
//        createFileLauncher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                result.data?.data?.let { uri ->
//                    _fileCreationCallback?.invoke(uri)
//                }
//            }
//        }
//    }

    fun writeToFile(context: Context, fileName: String, content: String) {
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(content.toByteArray())
            }
            Log.d("file writing", "Successful!")
            Toast.makeText(context, "File saved successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.d("file writing", "Error: ${e.message}")
            Toast.makeText(context, "File creation failed. Error: ${e.message}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}