package feature.account.domain

class RegisterUser(
    private val userClient: UserClient
) {
    suspend fun invoke(newUser: NewUser): Boolean {
        return userClient.newUser(newUser)
    }
}

class EmailAddressIsAlreadyInUseException : Exception(
    "Este correo electronico esta siendo utilizado, prueba recuperando contraseña o con otro correo"
)