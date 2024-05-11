package platform.validators.domain.exception

class DotComInBlacklistException(dotCom: String) : Exception(
    "This $dotCom is not valid extension, please change your email"
)