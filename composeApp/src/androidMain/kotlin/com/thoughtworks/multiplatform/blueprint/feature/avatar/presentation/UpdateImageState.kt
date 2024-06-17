package com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation

data class UpdateImageState(
    val isLoading: Boolean,
    val isSelectedImage: Boolean,
    val urlAvatar: String,
    val name: String,
    val message: String
) {
    companion object {
        fun default() = UpdateImageState(
            false,
            false,
            "",
            "",
            ""
        )
    }

    fun isEqualTo(other: UpdateImageState): Boolean {
        return this == other
    }
}