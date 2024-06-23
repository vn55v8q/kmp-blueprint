package com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import feature.account.domain.AuthenticationException
import kotlinx.coroutines.tasks.await
import platform.theme.domain.NotThemeFoundException
import platform.theme.domain.ThemeRemoteClient
import platform.theme.domain.ThemeSelected
import platform.theme.domain.ThemeType

class FirebaseThemeRemoteClient(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ThemeRemoteClient {

    companion object {
        private const val THEME_COLLECTION = "themes"
        private const val THEME_TYPE_KEY = "type"
        private const val THEME_DARK_KEY = "dark"
    }

    override suspend fun save(themeType: ThemeType, isDark: Boolean) {
        try {
            val id = firebaseAuth.currentUser?.uid.orEmpty()
            if (id.isEmpty()) {
                throw AuthenticationException()
            } else {
                val themeRef = firestore.collection(THEME_COLLECTION)
                val themeModel = ThemeModel(
                    themeType.name,
                    isDark
                )
                themeRef.document(id).set(themeModel)
            }
        } catch (e: Exception) {
            // TODO : Trackear en Crashlytics
        }
    }

    override suspend fun get(): ThemeSelected {
        try {
            val id = firebaseAuth.currentUser?.uid.orEmpty()
            if (id.isEmpty()) {
                throw AuthenticationException()
            } else {
                val themeData = firestore.collection(THEME_COLLECTION).document(id).get().await().data
                val type = themeData?.get(THEME_TYPE_KEY) as String
                val isDark = themeData[THEME_DARK_KEY] as Boolean
                return ThemeSelected(
                    type = getTypeToEntity(type),
                    isDark = isDark
                )
            }
        } catch (e: Exception){
            // TODO : Trackear en Craslytics
            throw NotThemeFoundException()
        }
    }

    private fun getTypeToEntity(type: String) : ThemeType {
        ThemeType.entries.forEach { typeItem ->
            if(type.lowercase() == typeItem.name.lowercase()){
                return typeItem
            }
        }
        throw NotThemeFoundException()
    }
}