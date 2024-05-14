package platform.validators.domain

import platform.log.Log
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
        val tag = "IsInvalidEmail"
        try {
            Log.d(tag, "init")
            val emailEntity = parseEmailToEntity.parse(email)
            Log.d(tag, "emailEntity: $emailEntity")
            val names = SplitName.invoke(emailEntity.name)
            Log.d(tag, "nanes: $names")
            names.forEach { name ->
                val nameWithoutNumbers = RemoveNumberInString.invoke(name)
                Log.d(tag, "IsInvalidName: $nameWithoutNumbers")
                if (isInvalidName.invoke(nameWithoutNumbers, 1)) {
                    Log.d(tag, "IsInvalidName: $nameWithoutNumbers")
                    throw NameInBlackListException(name)
                }
            }
            Log.d(tag, "domain: ${emailEntity.domain}")
            return if (isInvalidDomain.invoke(emailEntity.domain)) {
                Log.d(tag, "isInvalidDomain: ${emailEntity.domain}")
                throw EmailDomainInBlacklistException(emailEntity.domain)
            } else if (isInvalidDotCom.invoke(emailEntity.dotCom)) {
                Log.d(tag, "isInvalidDotCom: ${emailEntity.dotCom}")
                throw DotComInBlacklistException(emailEntity.dotCom)
            } else {
                Log.d(tag, "valid email")
                false
            }
        } catch (e: EmailFormatException) {
            Log.d(tag, "email format exception")
            return true
        }
    }
}
