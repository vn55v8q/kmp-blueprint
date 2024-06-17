package feature.account.domain

open class AccountException(override val message: String?) : Exception(message)

class AuthenticationException : AccountException("Usuario no authenticado")