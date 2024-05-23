package platform.validators.domain.exception

class NameInBlackListException(name: String) : Exception(
    "This name: $name exists on the blacklist, please change name"
)