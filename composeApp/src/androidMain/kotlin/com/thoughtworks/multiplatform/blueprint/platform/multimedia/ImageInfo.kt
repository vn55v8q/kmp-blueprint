package com.thoughtworks.multiplatform.blueprint.platform.multimedia

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import feature.account.domain.TypeImage
import platform.log.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object ImageInfo {

    fun getMimeType(context: Context, uri: Uri): String? {
        val contentResolver: ContentResolver = context.contentResolver
        return contentResolver.getType(uri)
    }


    fun getFileName(context: Context, uri: Uri): String {
        var name = ""
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst()) {
                name = cursor.getString(nameIndex)
            }
        }
        return name
    }

    fun getFileFromUri(context: Context, uri: Uri): File {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, getFileName(context, uri))
        val outputStream = FileOutputStream(file)
        inputStream?.use { input ->
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // Buffer de 4KB
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
        }
        return file
    }

    fun getTypeImage(context: Context, uri: Uri?) : TypeImage {
        if (uri == null) throw RuntimeException("Context is null for obtain type image")
        val mimeType = getMimeType(context, uri)

        // Verificar el tipo de imagen
        return when (mimeType) {
            "image/jpeg" -> TypeImage.JPG
            "image/png" -> TypeImage.PNG
            else -> TypeImage.UNSUPPORTED
        }
    }

}