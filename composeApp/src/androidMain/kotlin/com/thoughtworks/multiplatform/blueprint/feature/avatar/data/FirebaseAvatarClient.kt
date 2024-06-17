package com.thoughtworks.multiplatform.blueprint.feature.avatar.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import feature.account.domain.AuthenticationException
import feature.account.domain.ImageReference
import feature.account.domain.UrlReference
import feature.avatar.domain.Avatar
import feature.avatar.domain.AvatarClient
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class FirebaseAvatarClient(
    private val context: Context,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : AvatarClient {
    companion object {
        private const val MAX_IMAGE_WEITH = 10
        private const val USERS_KEY = "users"
        private const val USER_KEY = "user"
        private const val AVATAR_KEY = "avatar"
    }
    override suspend fun getAvatar(): Avatar {
        return try {
            val currentUser = auth.currentUser
            val id = currentUser?.uid.orEmpty()
            val name = currentUser?.displayName.orEmpty()
            if (id.isEmpty()) {
                throw AuthenticationException()
            }
            val userData = firestore.collection(USERS_KEY).document(id).get().await().data
            val user = userData?.get(USER_KEY) as String
            val avatarName = try {
                userData[AVATAR_KEY] as String
            } catch (e: Exception) {
                ""
            }
            var downloadUrl = ""
            if (avatarName.isNotEmpty()) {
                val storageRef = storage.reference.child("profile/$id/$avatarName")
                downloadUrl = try {
                    storageRef.downloadUrl.await().toString()
                } catch (e: Exception) {
                    ""
                }
            }
            Avatar(
                name = name,
                urlImage = downloadUrl,
                user = user
            )
        } catch (e: Exception) {
            // TODO : Registrar en Crashlytics
            // TODO : Revisar permisos en Firebase Datastore, tiene fecha
            //  match /{document=**} {
            //      allow read, write: if request.time < timestamp.date(2024, 7, 9);
            //  }
            Avatar.empty()
        }
    }

    override suspend fun uploadImage(imageReference: ImageReference): UrlReference {
        try {
            val currentUser = auth.currentUser
            val id = currentUser?.uid.orEmpty()
            if (id.isEmpty()) {
                throw AuthenticationException()
            }
            val imageName = "profile" + "." + imageReference.type.name.lowercase()
            val storageRef = storage.reference.child("profile/$id/$imageName")
            val uriImage = Uri.parse(imageReference.rawUri)

            // Load the image from the Uri
            val inputStream = context.contentResolver.openInputStream(uriImage)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            val outputStream = ByteArrayOutputStream()
            var quality = 100
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            var imageData = outputStream.toByteArray()

            // Check the size of the image
            while (imageData.size > MAX_IMAGE_WEITH * 1024 && quality > 0) {
                outputStream.reset()
                quality -= 5
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
                imageData = outputStream.toByteArray()
            }

            storageRef.putBytes(imageData).await()
            val downloadUrl = storageRef.downloadUrl.await().toString()
            val userRef = firestore.collection(USERS_KEY)
            userRef.document(id).update(AVATAR_KEY, imageName).await()
            return UrlReference(downloadUrl)
        } catch (e: Exception) {
            return UrlReference.empty()
        }
    }
}