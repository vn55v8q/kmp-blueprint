package platform.validators.domain

class PasswordValidator {
    fun invoke(password: String): PasswordStrength {
        if (password.length < 8 || password.length > 50) {
            return if (password.isEmpty()) {
                PasswordStrength.EMPTY
            } else {
                PasswordStrength.INSUFFICIENT
            }
        }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        val isLongPassword = password.length > 12
        val strengthPoints =
            listOf(hasLowerCase, hasUpperCase, hasDigit, hasSpecialChar, isLongPassword).count { it }

        return when (strengthPoints) {
            0 -> PasswordStrength.INSUFFICIENT
            1, 2 -> PasswordStrength.COMMON
            3, 4  -> PasswordStrength.GOOD
            else -> PasswordStrength.EXCELLENT
        }
    }
}

enum class PasswordStrength(
    val security: String,
    val message: String,
    val isValid: Boolean
) {
    EMPTY(
        "",
        "Las contraseñas fuertes incluyen una combinación de letras, minúsculas, mayúsculas, números y caracteres especiales",
        false
    ),
    INSUFFICIENT(
        "Baja",
        "Contraseña insuficiente",
        false
    ),
    COMMON(
        "Insuficiente",
        "Contraseña común",
        false
    ),
    GOOD(
        "Buena",
        "Contraseña buena",
        true
    ),
    EXCELLENT(
        "Excelente",
        "Contraseña excelente",
        true
    )
}
