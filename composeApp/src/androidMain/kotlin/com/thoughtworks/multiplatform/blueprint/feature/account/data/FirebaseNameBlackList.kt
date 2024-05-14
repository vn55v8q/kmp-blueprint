package com.thoughtworks.multiplatform.blueprint.feature.account.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import platform.log.Log
import platform.validators.domain.BlackListClient
import platform.validators.domain.exception.NotFoundNameDataException
import platform.validators.domain.exception.NotFoundVersionForClientExeption

class FirebaseNameBlackList(
    private val firestoreDatabase: FirebaseFirestore,
) : BlackListClient<String> {

    override val id: String = "firebase-name-client"
    override var version: Int = 0
    override var mutablaList: MutableList<String> = mutableListOf()

    override suspend fun syncData() {
        val tag = "UpdateLocalString"
        Log.d(tag, "init FirebaseNameBlackList sync data")
        try {
            val blackListRef =
                firestoreDatabase.collection(BLACK_LIST_KEY)
                    .document(NAMES_KEY)
                    .collection(NAME_KEY)
            mutablaList.clear()
            val querySnapshot = blackListRef.get().await()
            Log.d(tag, "list name size : ${querySnapshot.documents.size}")
            querySnapshot.documents.forEach { document ->
                val word = document.getString(VALUE_KEY).orEmpty()
                Log.d(tag, "word : $word")
                if (word.isNotEmpty()) {
                    Log.d(tag, "word : $word")
                    mutablaList.add(word)
                }
            }
            Log.d(tag, "return list : $mutablaList")
        } catch (e: Exception) {
            Log.d(tag, "error : ${e.message}")
            throw NotFoundNameDataException()
        }
    }

    override suspend fun syncAndGetVersion(): Int {
        val versionRef = firestoreDatabase.collection(BLACK_LIST_KEY).document(NAMES_KEY)
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
        const val NAME_KEY = "name"
        const val NAMES_KEY = "names"
        const val VERSION_KEY = "version"
        const val VALUE_KEY = "value"
    }

}