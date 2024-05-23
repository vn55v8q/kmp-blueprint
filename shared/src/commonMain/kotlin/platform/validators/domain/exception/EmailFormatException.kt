package platform.validators.domain.exception

class EmailFormatException(email: String) : Exception(
    "The email format is incorrect [$email]"
)