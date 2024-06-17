package com.thoughtworks.multiplatform.blueprint.feature.profile.data

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
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
        private const val UPDATE_USER_KEY = "update.user"
        private const val UPDATE_DESCRIPTION_KEY = "update.description"
        private const val UPDATE_PRONOUN_KEY = "update.pronoun"
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

    private suspend fun changeValue(
        key: String,
        newValue: String,
        timeKey: String
    ): Boolean {
        try {
            val currentUser = auth.currentUser
            val id = currentUser?.uid.orEmpty()
            if (id.isEmpty()) {
                throw AuthenticationException()
            }
            val userRef = firestore.collection(USERS_KEY)
            userRef
                .document(id)
                .update(
                    mapOf(
                        key to newValue,
                        timeKey to Timestamp.now()
                    )
                )
                .await()
            return true
        } catch (e: Exception) {
            // TODO : Add to Crashlytics
            return false
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
            return changeValue(NAME_KEY, newName, UPDATE_NAME_KEY)
        } catch (e: Exception) {
            // TODO : Add to Crashlytics
            return false
        }
    }

    override suspend fun changeUser(newName: String): Boolean {
        return changeValue(USER_KEY, newName, UPDATE_USER_KEY)
    }

    override suspend fun changeDescription(newDescription: String): Boolean {
        return changeValue(DESCRIPTION_KEY, newDescription, UPDATE_DESCRIPTION_KEY)
    }

    override suspend fun changePronoun(newPronoun: String): Boolean {
        return changeValue(PRONOUN_KEY, newPronoun, UPDATE_PRONOUN_KEY)
    }


    override suspend fun isChangeNameEnabled(): Boolean {
        // TODO : Add rule to enable or disable changes on application
        return true
    }

    override suspend fun isChangeUserEnabled(): Boolean {
        // TODO : Add rule to enable or disable changes on application
        return true
    }

    override suspend fun isChangePronounEnabled(): Boolean {
        // TODO : Add rule to enable or disable changes on application
        return true
    }

    override suspend fun isChangeDescriptionEnabled(): Boolean {
        // TODO : Add rule to enable or disable changes on application
        return true
    }
}