package com.thoughtworks.multiplatform.blueprint.feature.onboarding.data

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import feature.onboarding.domain.ClientOnboarding
import feature.onboarding.domain.Onboarding
import feature.onboarding.domain.PageOnboarding
import kotlinx.coroutines.tasks.await
import platform.log.Log

class FirebaseClientOnboarding(
    private val storage: FirebaseStorage
) : ClientOnboarding {

    override suspend fun get(): Onboarding {
        val storageRef = storage.reference
        val imagesRef: StorageReference = storageRef.child("onboarding/scene")
        val pages = mutableListOf<PageOnboarding>()
        try {
            val listResult = imagesRef.listAll().await()
            listResult.items.forEachIndexed { index, ref ->
                val urlImage = ref.downloadUrl.await()
                pages.add(
                    PageOnboarding(
                        "$index",
                        ref.name,
                        url = urlImage.toString()
                    )
                )
            }
            return Onboarding(
                id = "firebase",
                name = "Firebase Onboarding Storage",
                pages = pages
            )
        } catch (e: Exception) {
            // TODO: Add this log to Crashlytics
            Log.d("FirebaseClient", "Error: $e")
            return Onboarding.empty()
        }
    }
}