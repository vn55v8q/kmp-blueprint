package com.thoughtworks.multiplatform.blueprint.feature.account.data

import android.net.Uri
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.thoughtworks.multiplatform.blueprint.feature.account.data.model.RegisterUserModel
import feature.account.domain.Account
import feature.account.domain.AuthenticationException
import feature.account.domain.EmailAddressIsAlreadyInUseException
import feature.account.domain.ImageReference
import feature.account.domain.NewUser
import feature.account.domain.UrlReference
import feature.account.domain.UserClient
import kotlinx.coroutines.tasks.await
import platform.log.Log

class FirebaseUserClient(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : UserClient {

    companion object {
        private const val USERS_KEY = "users"
        private const val USER_KEY = "user"
        private const val PRONOUN_KEY = "pronoun"
        private const val DESCRIPTION_KEY = "description"
        private const val AVATAR_KEY = "avatar"
    }

    override suspend fun newUser(newUser: NewUser): Boolean {
        try {
            auth.createUserWithEmailAndPassword(newUser.email, newUser.password).await()
            val currentUser = auth.currentUser
            saveNameInFirebaseAuth(currentUser, newUser.name)
            val userRef = firestore.collection(USERS_KEY)
            val userId = auth.currentUser?.uid.orEmpty()
            with(newUser) {
                val userModel = RegisterUserModel(
                    name = name, user = user, email = email, age = age
                )
                userRef.document(userId).set(userModel).await()
                return true
            }
        } catch (e: FirebaseAuthUserCollisionException) {
            throw EmailAddressIsAlreadyInUseException()
        } catch (e: Exception) {
            return false
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
            storageRef.putFile(uriImage).await()
            val downloadUrl = storageRef.downloadUrl.await().toString()
            val userRef = firestore.collection(USERS_KEY)
            userRef.document(id).update(AVATAR_KEY, imageName).await()
            return UrlReference(downloadUrl)
        } catch (e: Exception) {
            Log.d("FirebaseUserClient", "Exception : $e")
            return UrlReference.empty()
        }
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            val currentUser = auth.currentUser
            return currentUser?.uid.orEmpty().isNotEmpty()
        } catch (e: FirebaseTooManyRequestsException) {
            // TODO : Manejar este error... FirebaseTooManyRequestsException: We have blocked all requests from this device due to unusual activity. Try again later. [ Access to this account has been temporarily disabled due to many failed login attempts. You can immediately restore it by resetting your password or you can try again later. ]
            return false
        } catch (e: Exception) {
            return false
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

    override suspend fun getAccount(): Account {
        try {
            val currentUser = auth.currentUser
            val id = currentUser?.uid.orEmpty()
            val name = currentUser?.displayName
            if (id.isEmpty()) {
                throw AuthenticationException()
            }
            val userData = firestore.collection(USERS_KEY).document(id).get().await().data
            val user = userData?.get(USER_KEY) as String
            val pronoun = userData[PRONOUN_KEY] as String
            val description = userData[DESCRIPTION_KEY] as String
            val avatarName = userData[AVATAR_KEY] as String

            val storageRef = storage.reference.child("profile/$id/$avatarName")
            val downloadUrl = try {
                storageRef.downloadUrl.await().toString()
            } catch (e: Exception) {
                ""
            }

            return Account(
                id = id,
                name = name.orEmpty(),
                user = "@$user",
                pronoun = pronoun,
                description = description,
                urlAvatar = downloadUrl
            )
        } catch (e: Exception) {
            return Account.empty()
        }
    }
}

