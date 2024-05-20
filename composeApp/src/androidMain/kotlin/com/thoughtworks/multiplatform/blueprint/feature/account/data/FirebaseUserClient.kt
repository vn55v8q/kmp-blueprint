package com.thoughtworks.multiplatform.blueprint.feature.account.data

import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import feature.account.domain.EmailAddressIsAlreadyInUseException
import feature.account.domain.ImageReference
import feature.account.domain.NewUser
import feature.account.domain.UrlReference
import feature.account.domain.UserClient
import kotlinx.coroutines.tasks.await

class FirebaseUserClient(
    private val firebaseAuth: FirebaseAuth, private val firestore: FirebaseFirestore
) : UserClient {

    companion object {
        private const val USER_KEY = "users"
    }

    override suspend fun newUser(newUser: NewUser): Boolean {
        try {
            firebaseAuth.createUserWithEmailAndPassword(newUser.email, newUser.password).await()
            val currentUser = firebaseAuth.currentUser
            saveNameInFirebaseAuth(currentUser, newUser.name)
            val userRef = firestore.collection(USER_KEY)
            val userId = firebaseAuth.currentUser?.uid.orEmpty()
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
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val currentUser = firebaseAuth.currentUser
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
}

data class RegisterUserModel(
    val user: String,
    val name: String,
    val email: String,
    val age: Int,
)