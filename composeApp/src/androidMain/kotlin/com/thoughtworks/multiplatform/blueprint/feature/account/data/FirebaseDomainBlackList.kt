package com.thoughtworks.multiplatform.blueprint.feature.account.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import platform.log.Log
import platform.validators.domain.BlackListClient
import platform.validators.domain.exception.NotFoundNameDataException
import platform.validators.domain.exception.NotFoundVersionForClientExeption

class FirebaseDomainBlackList(
    private val firestoreDatabase: FirebaseFirestore,
) : BlackListClient<String> {

    override val id: String = "firebase-domain-client"
    override var version: Int = 0
    override var mutablaList: MutableList<String> = mutableListOf()

    override suspend fun syncData() {
        try {
            val blackListRef =
                firestoreDatabase.collection(BLACK_LIST_KEY)
                    .document(DOMAIN_KEY)
                    .collection(NAME_KEY)
            mutablaList.clear()
            val querySnapshot = blackListRef.get().await()
            Log.d("FirebaseDomainBlackList", "version: $version")
            Log.d("FirebaseDomainBlackList", "documents size: ${querySnapshot.documents.size}")
            querySnapshot.documents.forEach { document ->
                val word = document.getString(VALUE_KEY).orEmpty()
                if (word.isNotEmpty()) {
                    Log.d("FirebaseDomainBlackList", "word: $word")
                    mutablaList.add(word)
                }
            }
        } catch (e: Exception) {
            throw NotFoundNameDataException()
        }
    }

    override suspend fun syncAndGetVersion(): Int {
        Log.d("FirebaseDomainBlackList", "syncAndGetVersion()")
        Log.d("FirebaseDomainBlackList", "verion: $version")
        val versionRef = firestoreDatabase.collection(BLACK_LIST_KEY).document(DOMAIN_KEY)
        val document = versionRef.get().await()
        val remoteVersion = document.getString(VERSION_KEY)
        if (!remoteVersion.isNullOrEmpty()) {
            version = remoteVersion.toInt()
            Log.d("FirebaseDomainBlackList", "new verion: $version")
            return version
        } else {
            throw NotFoundVersionForClientExeption()
        }
    }

    companion object {
        const val BLACK_LIST_KEY = "blacklist"
        const val DOMAIN_KEY = "domain"
        const val NAME_KEY = "name"
        const val VERSION_KEY = "version"
        const val VALUE_KEY = "value"
    }

}
