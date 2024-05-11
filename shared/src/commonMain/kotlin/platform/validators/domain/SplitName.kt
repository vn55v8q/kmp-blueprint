package platform.validators.domain

object SplitName {
    fun invoke(name: String): List<String> {
        val regex = Regex("[^a-zA-Z0-9]+")
        return name.split(regex)
    }
}