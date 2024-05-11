package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import platform.validators.domain.IsInvalidName
import platform.validators.domain.UpdateLocalString

class AccountViewModel(
    private val nameUpdateLocalString: UpdateLocalString,
    private val isInvalidName: IsInvalidName
) : ViewModel() {

    init {
        viewModelScope.launch {
            nameUpdateLocalString.invoke()
        }
    }

    private val mutableStateFlow = MutableStateFlow(AccountState.default())
    val state = mutableStateFlow.asStateFlow()

    fun processName(name: String) {
        viewModelScope.launch {
            val isInvalidName = isInvalidName.invoke(name)
            Log.d("Firestore", "isInvalidName: $isInvalidName......$name")
            mutableStateFlow.update {
                it.copy(
                    isValidName = !isInvalidName,
                    name = name
                )
            }
        }
    }
}

data class User(
    val name: String
)

data class AccountState(
    val isValidName: Boolean,
    val isValidUser: Boolean,
    val isValidEmail: Boolean,
    val isValidPassword: Boolean,
    val user: String,
    val name: String,
    val email: String,
    val password: String
) {
    companion object {
        fun default() = AccountState(
            isValidName = false,
            isValidUser = false,
            isValidEmail = false,
            isValidPassword = false,
            user = "",
            name = "",
            email = "",
            password = ""
        )
    }
}