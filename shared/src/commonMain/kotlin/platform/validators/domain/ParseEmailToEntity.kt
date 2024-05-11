package platform.validators.domain

import platform.validators.domain.entity.Email
import platform.validators.domain.exception.EmailFormatException

class ParseEmailToEntity {
    fun parseEmail(email: String): Email {
        /*val regex = "([\\w.-]+)_?([\\w.-]+)?@([\\w-]+\\.[\\w.-]+)".toRegex()
        val matchResult = regex.matchEntire(email)
            ?: throw EmailFormatException(email)
        val name =
            matchResult.groupValues[1] + if (matchResult.groupValues[2].isNotEmpty()) ".${matchResult.groupValues[2]}" else ""
        val domain = matchResult.groupValues[3]
        val dotCom = matchResult.groupValues[4]
        return Email(name, domain, dotCom)*/
        /*val parts = email.split("@")
        if (parts.size != 2) {
            throw EmailFormatException(email)
        }
        val name = parts[0].substringBeforeLast('.')
        val domainAndExt = parts[1].split('.')
        if (domainAndExt.isEmpty()) {
            throw EmailFormatException(email)
        }
        val domain = domainAndExt[0]
        val ext = domainAndExt.subList(1, domainAndExt.size).joinToString(".")
        return Email(name, domain, ext)*/
        val regex = "([\\w.-]+)_?([\\w.-]+)?@([\\w-]+\\.[\\w.-]+)".toRegex()
        val matchResult = regex.matchEntire(email)
            ?: throw EmailFormatException(email)
        val name =
            matchResult.groupValues[1] + if (matchResult.groupValues[2].isNotEmpty()) ".${matchResult.groupValues[2]}" else ""
        val domainAndExt = matchResult.groupValues[3].split('.')
        require(domainAndExt.size >= 2) { "Invalid email format: $email" }
        val domain = domainAndExt[0]
        val ext = domainAndExt.subList(1, domainAndExt.size).joinToString(".")
        val extCount = ext.split(".")
        if (extCount.size > 2) {
            throw EmailFormatException(email)
        }
        return Email(name, domain, ext)
    }
}