package com.thoughtworks.multiplatform.blueprint.feature.profile.data

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thoughtworks.multiplatform.blueprint.feature.account.data.FirebaseUserClient
import feature.account.domain.AuthenticationException
import feature.profile.domain.Profile
import feature.profile.domain.ProfileClient
import kotlinx.coroutines.tasks.await

class FirebaseProfileClient(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : ProfileClient {

    companion object {
        private const val USERS_KEY = "users"
        private const val USER_KEY = "user"
        private const val AVATAR_KEY = "avatar"
        private const val PRONOUN_KEY = "pronoun"
        private const val DESCRIPTION_KEY = "description"
        private const val NAME_KEY = "name"
        private const val UPDATE_NAME_KEY = "update.name"
    }

    override suspend fun getProfile(): Profile {
        return try {
            val currentUser = auth.currentUser
            val id = currentUser?.uid.orEmpty()
            val name = currentUser?.displayName.orEmpty()
            if (id.isEmpty()) {
                throw AuthenticationException()
            }
            val userData = firestore.collection(USERS_KEY).document(id).get().await().data
            val user = userData?.get(USER_KEY) as String
            val pronoun = userData[PRONOUN_KEY] as String
            val imageName = userData[AVATAR_KEY] as String
            val description = userData[DESCRIPTION_KEY] as String
            val urlAvatar = if (imageName.isEmpty()) {
                ""
            } else {
                val storageRef = storage.reference.child("profile/$id/$imageName")
                try {
                    storageRef.downloadUrl.await().toString()
                } catch (e: Exception) {
                    ""
                }
            }
            Profile(
                name = name,
                description = description,
                pronoun = pronoun,
                user = user,
                urlAvatar = urlAvatar
            )
        } catch (e: Exception) {
            // TODO : Registrar en Crashlytics
            Profile.empty()
        }

    }

    private fun saveNameInFirebaseAuth(currentUser: FirebaseUser?, name: String) {
        currentUser?.let { safeUser ->
            val updateProfileRequest = userProfileChangeRequest {
                displayName = name
            }
            safeUser.updateProfile(updateProfileRequest)
        }
    }

    override suspend fun changeName(newName: String): Boolean {
        try {
            val currentUser = auth.currentUser
            val id = currentUser?.uid.orEmpty()
            if (id.isEmpty()) {
                throw AuthenticationException()
            }
            saveNameInFirebaseAuth(currentUser, newName)
            val userRef = firestore.collection(USERS_KEY)
            userRef
                .document(id)
                .update(
                    mapOf(
                        NAME_KEY to newName,
                        UPDATE_NAME_KEY to Timestamp.now()
                    )
                )
                .await()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun isChangeNameEnabled(): Boolean {
        return true
    }

    override suspend fun isChangeUserEnabled(): Boolean {
        return true
    }

    override suspend fun isChangePronounEnabled(): Boolean {
        return true
    }

    override suspend fun isChangeDescriptionEnabled(): Boolean {
        return true
    }
}