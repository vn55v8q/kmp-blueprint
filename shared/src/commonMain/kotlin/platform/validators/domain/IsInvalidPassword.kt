package platform.validators.domain

class IsInvalidPassword() {
    suspend fun invoke(password: String): Boolean {
        // TODO : Agregar logica de clave
        return password.length < 6
    }
}
