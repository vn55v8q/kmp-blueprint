package com.thoughtworks.multiplatform.blueprint.feature.account.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import platform.validators.domain.BlackListClient
import platform.validators.domain.exception.NotFoundNameDataException
import platform.validators.domain.exception.NotFoundVersionForClientExeption

class FirebaseDotComBlackList(
    private val firestoreDatabase: FirebaseFirestore,
) : BlackListClient<String> {

    override val id: String = "firebase-dot-com-client"
    override var version: Int = 0
    override var mutablaList: MutableList<String> = mutableListOf()

    override suspend fun syncData() {
        try {
            val blackListRef =
                firestoreDatabase.collection(BLACK_LIST_KEY)
                    .document(DOT_COM_KEY)
                    .collection(NAMES_KEY)
            mutablaList.clear()
            val querySnapshot = blackListRef.get().await()
            querySnapshot.documents.forEach { document ->
                val word = document.getString(VALUE_KEY).orEmpty()
                if (word.isNotEmpty()) {
                    mutablaList.add(word)
                }
            }
        } catch (e: Exception) {
            throw NotFoundNameDataException()
        }
    }

    override suspend fun syncAndGetVersion(): Int {
        val versionRef = firestoreDatabase.collection(BLACK_LIST_KEY).document(DOT_COM_KEY)
        val document = versionRef.get().await()
        val remoteVersion = document.getString(VERSION_KEY)
        if (!remoteVersion.isNullOrEmpty()) {
            version = remoteVersion.toInt()
            return version
        } else {
            throw NotFoundVersionForClientExeption()
        }
    }

    companion object {
        const val BLACK_LIST_KEY = "blacklist"
        const val DOT_COM_KEY = "dot-com"
        const val NAMES_KEY = "name"
        const val VERSION_KEY = "version"
        const val VALUE_KEY = "value"
    }

}
