package com.thoughtworks.multiplatform.blueprint.feature.account.data.model

data class RegisterUserModel(
    val user: String,
    val name: String,
    val email: String,
    val pronoun: String = "",
    val description: String = "",
    val age: Int,
)