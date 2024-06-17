package com.thoughtworks.multiplatform.blueprint.feature.profile.presentation

data class ProfileState(
    val isLoading: Boolean,
    val isFinishFlow: Boolean,
    val urlImage: String,
    val name: String,
    val user: String,
    val pronoun: String,
    val shortDescription: String,
    val message: String,
) {
    companion object {
        fun default() = ProfileState(
            isLoading = true,
            isFinishFlow = false,
            urlImage = "",
            name = "",
            user = "",
            pronoun = "",
            shortDescription = "",
            message = ""
        )
    }
    fun isEqualTo(other: ProfileState): Boolean {
        return this == other
    }
}