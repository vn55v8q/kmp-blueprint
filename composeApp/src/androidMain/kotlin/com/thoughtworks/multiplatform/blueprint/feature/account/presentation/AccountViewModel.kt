package com.thoughtworks.multiplatform.blueprint.feature.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.account.domain.EmailAddressIsAlreadyInUseException
import feature.account.domain.NewUser
import feature.account.domain.RegisterUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import platform.log.Log
import platform.validators.domain.IsInvalidEmail
import platform.validators.domain.IsInvalidName
import platform.validators.domain.IsInvalidPassword
import platform.validators.domain.UpdateLocalString
import platform.validators.domain.exception.DotComInBlacklistException
import platform.validators.domain.exception.EmailDomainInBlacklistException
import platform.validators.domain.exception.NameInBlackListException

class AccountViewModel(
    private val nameUpdateLocalString: UpdateLocalString,
    private val domainUpdateLocalString: UpdateLocalString,
    private val dotComUpdateLocalString: UpdateLocalString,
    private val isInvalidName: IsInvalidName,
    private val isInvalidEmail: IsInvalidEmail,
    private val isInvalidPassword: IsInvalidPassword,
    private val registerUser: RegisterUser
) : ViewModel() {

    init {
        viewModelScope.launch {
            nameUpdateLocalString.invoke()
            domainUpdateLocalString.invoke()
            dotComUpdateLocalString.invoke()
        }
    }

    private val sessionState = MutableStateFlow(NewUser.default())
    private val emailBlackListState = MutableStateFlow(mutableListOf<String>())
    private val mutableStateFlow = MutableStateFlow(AccountState.default())
    val state = mutableStateFlow.asStateFlow()

    fun processEmail(email: String) {
        viewModelScope.launch {
            try {
                Log.d("AccountViewModel", "email : $email")
                val existEmailRegistered = emailBlackListState.value.filter { emailItem ->
                    emailItem == email
                }.isNotEmpty()
                if (existEmailRegistered) {
                    mutableStateFlow.update {
                        it.copy(
                            isValidEmail = false,
                            errorMessage = "Este correo ya existe, recupera contraseña o utiliza otro email",
                            currentStep = 0
                        )
                    }
                } else {
                    val isInvalidEmail = isInvalidEmail.invoke(email)
                    Log.d("AccountViewModel", "isValidMail $isInvalidEmail")
                    if (isInvalidEmail) {
                        mutableStateFlow.update {
                            it.copy(
                                isValidEmail = false,
                                errorMessage = "Este correo no es permitido",
                                currentStep = 0
                            )
                        }
                    } else {
                        sessionState.update {
                            it.copy(
                                email = email
                            )
                        }
                        mutableStateFlow.update {
                            it.copy(
                                isValidEmail = true, errorMessage = null, currentStep = 1
                            )
                        }
                    }
                }
            } catch (name: NameInBlackListException) {
                Log.d("AccountViewModel", "NameInBlackListException")
                mutableStateFlow.update {
                    it.copy(
                        isValidEmail = false,
                        errorMessage = "El nombre del email no es permitido en nuestros registros",
                        currentStep = 0
                    )
                }
            } catch (domain: EmailDomainInBlacklistException) {
                Log.d("AccountViewModel", "EmailDomainInBlacklistException")
                mutableStateFlow.update {
                    it.copy(
                        isValidEmail = false,
                        errorMessage = "El dominio del email no es permitido",
                        currentStep = 0
                    )
                }
            } catch (dotCom: DotComInBlacklistException) {
                Log.d("AccountViewModel", "DotComInBlacklistException")
                mutableStateFlow.update {
                    it.copy(
                        isValidEmail = false,
                        errorMessage = "La extensión del correo no es permitido",
                        currentStep = 0
                    )
                }
            }
        }
    }

    fun processUser(user: String) {
        viewModelScope.launch {
            val isInvalidName = isInvalidName.invoke(user)
            if (isInvalidName) {
                mutableStateFlow.update {
                    it.copy(
                        isValidUser = false,
                        errorMessage = "El nombre no corresponde"
                    )
                }
            } else {
                sessionState.update {
                    it.copy(
                        user = user
                    )
                }
                mutableStateFlow.update {
                    it.copy(
                        isValidUser = true,
                        errorMessage = "",
                        currentStep = 3
                    )
                }
            }

        }
    }

    fun processName(name: String) {
        viewModelScope.launch {
            val isInvalidName = isInvalidName.invoke(name)
            if (isInvalidName) {
                mutableStateFlow.update {
                    it.copy(
                        isValidName = false,
                        errorMessage = "El nombre no es permitido"
                    )
                }
            } else {
                sessionState.update {
                    it.copy(
                        name = name
                    )
                }
                mutableStateFlow.update {
                    it.copy(
                        isValidName = true,
                        currentStep = 2,
                        errorMessage = ""
                    )
                }
            }
        }
    }

    fun processPass(pass: String) {
        viewModelScope.launch {
            val isInvalidPass = isInvalidPassword.invoke(pass)
            if (isInvalidPass) {
                mutableStateFlow.update {
                    it.copy(
                        isValidPassword = false,
                        errorMessage = "La contraseña no cumple con lo minimo de seguridad"
                    )
                }
            } else {
                sessionState.update {
                    it.copy(
                        password = pass
                    )
                }
                createAccount()
            }

        }
    }

    fun createAccount() {
        viewModelScope.launch {
            mutableStateFlow.update {
                it.copy(
                    isLoading = true,
                    errorMessage = "Creating account",
                )
            }
            try {
                if (registerUser.invoke(sessionState.value)) {
                    mutableStateFlow.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "User create de pana",
                        )
                    }
                }
            } catch (e: EmailAddressIsAlreadyInUseException) {
                emailBlackListState.value.add(sessionState.value.email)
                mutableStateFlow.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message,
                        currentStep = 0
                    )
                }
            }
        }
    }

    fun clearErrorMessage() {
        mutableStateFlow.update {
            it.copy(
                errorMessage = null,
            )
        }
    }

    fun onLastStepProcess() {
        val newStep = state.value.currentStep - 1
        if (newStep >= 0) {
            mutableStateFlow.update {
                it.copy(
                    currentStep = newStep,
                )
            }
        }
    }


}

data class AccountState(
    val isLoading: Boolean,
    val currentStep: Int,
    val isValidName: Boolean,
    val isValidUser: Boolean,
    val isValidEmail: Boolean,
    val isValidPassword: Boolean,
    val isCreateUser: Boolean,
    val errorMessage: String? = null
) {
    companion object {
        fun default() = AccountState(
            isLoading = false,
            currentStep = 0,
            isValidName = false,
            isValidUser = false,
            isValidEmail = false,
            isValidPassword = false,
            isCreateUser = false
        )
    }
}