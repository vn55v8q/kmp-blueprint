package platform.validators.domain.exception

class EmailDomainInBlacklistException(domain: String) : Exception(
    "This domain: $domain is not valid, please check domain or change to another"
)