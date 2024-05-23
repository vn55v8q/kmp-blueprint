package platform.validators.domain

object RemoveNumberInString {
    fun invoke(value: String): String {
        return value.replace(Regex("[0-9]"), "")
    }
}

object ChangeNumberForLetter {
    fun invoke(value: String): String {
        return value.replace("4", "a")
            .replace("3", "e")
            .replace("1", "i")
            .replace("0", "o")
    }
}