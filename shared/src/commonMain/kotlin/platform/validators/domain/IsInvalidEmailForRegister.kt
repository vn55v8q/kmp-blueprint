package platform.validators.domain

import platform.validators.domain.exception.DotComInBlacklistException
import platform.validators.domain.exception.EmailDomainInBlacklistException
import platform.validators.domain.exception.EmailFormatException
import platform.validators.domain.exception.NameInBlackListException

class IsInvalidEmailForRegister(
    private val parseEmailToEntity: ParseEmailToEntity,
    private val isInvalidName: IsInvalidName,
    private val isInvalidDomain: IsInvalidEmailDomain,
    private val isInvalidDotCom: IsInvalidEmailDotCom
) {
    suspend fun invoke(email: String): Boolean {
        try {
            val emailEntity = parseEmailToEntity.parse(email)
            val names = SplitName.invoke(emailEntity.name)
            names.forEach { name ->
                val nameWithoutNumbers = RemoveNumberInString.invoke(name)
                if (isInvalidName.invoke(nameWithoutNumbers, 1)) {
                    throw NameInBlackListException(name)
                }
            }
            return if (isInvalidDomain.invoke(emailEntity.domain)) {
                throw EmailDomainInBlacklistException(emailEntity.domain)
            } else if (isInvalidDotCom.invoke(emailEntity.dotCom)) {
                throw DotComInBlacklistException(emailEntity.dotCom)
            } else {
                false
            }
        } catch (e: EmailFormatException) {
            return true
        }
    }
}
