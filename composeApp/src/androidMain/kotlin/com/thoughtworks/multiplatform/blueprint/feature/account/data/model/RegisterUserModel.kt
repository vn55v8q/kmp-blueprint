package com.thoughtworks.multiplatform.blueprint.feature.account.data.model

import com.google.firebase.Timestamp

data class RegisterUserModel(
    val user: String,
    val name: String,
    val email: String,
    val pronoun: String = "",
    val description: String = "",
    val age: Int,
    val update: UpdateModel = UpdateModel(),
    val created: Timestamp = Timestamp.now(),
)

data class UpdateModel(
    val user: Timestamp? = null,
    val name: Timestamp? = null,
    val pronoun: Timestamp? = null,
    val description: Timestamp? = null,
)



