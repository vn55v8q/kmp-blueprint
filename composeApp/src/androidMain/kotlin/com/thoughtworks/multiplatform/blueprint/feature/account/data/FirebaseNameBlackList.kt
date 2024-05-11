package com.thoughtworks.multiplatform.blueprint.feature.account.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import platform.validators.domain.BlackListClient
import platform.validators.domain.exception.NotFoundVersionForClientExeption

class FirebaseNameBlackList(
    private val firestoreDatabase: FirebaseFirestore,
) : BlackListClient<String> {

    override val id: String = "firebase-name-client"
    override var version: Int = 0
    override var mutablaList: MutableList<String> = mutableListOf()


    override suspend fun syncData() {
        try {
            Log.d("Firestore", "Init sync")
            val blackListRef =
                firestoreDatabase.collection("blacklist").document("names").collection("name")
            mutablaList.clear()
            val querySnapshot = blackListRef.get().await()
            querySnapshot.documents.forEach { document ->
                val word = document.getString("value").orEmpty()
                if (word.isNotEmpty()) {
                    Log.d("Firestore" , "New word found: $word")
                    mutablaList.add(word)
                }
            }
        } catch (e: Exception) {
            Log.d("Firestore", e.message.orEmpty())
        }
    }

    override suspend fun syncAndGetVersion(): Int {
        Log.d("Firestore", "Get Version mi chang")
        val versionRef = firestoreDatabase.collection("blacklist").document("names")
        val document = versionRef.get().await()
        val remoteVersion = document.getString("version")
        if (!remoteVersion.isNullOrEmpty()) {
            version = remoteVersion.toInt()
            Log.d("Firestore", "New remote version $version")
            return version
        } else {
            throw NotFoundVersionForClientExeption()
        }

    }

    companion object {
        const val KEY_DOCUMENT = "blacklist/name/names"
    }

}

data class NameValue(
    val value: String
)