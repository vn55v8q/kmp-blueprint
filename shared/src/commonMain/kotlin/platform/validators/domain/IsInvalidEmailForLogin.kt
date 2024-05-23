package platform.validators.domain

import platform.validators.domain.exception.EmailFormatException

class IsInvalidEmailForLogin(
    private val parseEmailToEntity: ParseEmailToEntity,
) {
    fun invoke(email: String): Boolean {
        try {
            val emailEntity = parseEmailToEntity.parse(email)
            return false
        } catch (e: EmailFormatException) {
            return true
        }
    }
}
