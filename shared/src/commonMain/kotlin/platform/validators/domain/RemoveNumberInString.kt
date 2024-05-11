package platform.validators.domain

object RemoveNumberInString {
    fun invoke(value: String): String {
        return value.replace(Regex("[0-9]"), "")
    }
}