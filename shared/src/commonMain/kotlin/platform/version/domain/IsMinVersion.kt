package platform.version.domain

object IsMinVersion {
    fun invoke(currentVersion: String, compareVersion: String): Boolean {
        validateVersions(currentVersion, compareVersion)
        val regex = "(\\d+)\\.(\\d+)\\.(\\d+)(?:-(alpha|beta|rc)(\\d+)?)?".toRegex()

        val matchCurrent = regex.find(currentVersion)
        val matchCompare = regex.find(compareVersion)

        if (matchCurrent != null && matchCompare != null) {
            val (majorCurrent, minorCurrent,
                patchCurrent, stageCurrent, stageNumberCurrent)
                    = extractVersionParts(matchCurrent)
            val (majorCompare, minorCompare,
                patchCompare, stageCompare, stageNumberCompare)
                    = extractVersionParts(matchCompare)
            return if (majorCurrent.toInt() != majorCompare.toInt()) {
                majorCurrent.toInt() > majorCompare.toInt()
            } else if (minorCurrent.toInt() != minorCompare.toInt()) {
                minorCurrent.toInt() > minorCompare.toInt()
            } else if (patchCurrent.toInt() != patchCompare.toInt()) {
                patchCurrent.toInt() > patchCompare.toInt()
            } else if (stageCurrent != stageCompare) {
                compareStage(stageCurrent, stageCompare)
            } else if (stageCurrent.isNotEmpty() && stageCurrent == stageCompare) {
                // If stages are the same, compare the numbers as strings
                stageNumberCurrent.toInt() >= stageNumberCompare.toInt()
            } else {
                stageCurrent.isEmpty() && stageCompare.isEmpty()
            }
        }
        return false
    }

    private fun validateVersions(currentVersion: String, compareVersion: String) {
        if (isValidVersionName(currentVersion).not()) {
            throw VersionFormatException(currentVersion)
        } else if (isValidVersionName(compareVersion).not()) {
            throw VersionFormatException(compareVersion)
        }
    }

    private fun isValidVersionName(name: String): Boolean {
        val regex = "(\\d+)\\.(\\d+)\\.(\\d+)(?:-(alpha|beta|rc)(\\d+)?)?".toRegex()
        return regex.matches(name)
    }

    private fun compareStage(currentStage: String, compareStage: String): Boolean {
        val stageOrder = mapOf("alpha" to 0, "beta" to 1, "rc" to 2, "" to 3)
        val currentOrder = stageOrder[currentStage] ?: -1
        val compareOrder = stageOrder[compareStage] ?: -1
        return currentOrder >= compareOrder
    }

    private fun extractVersionParts(match: MatchResult): List<String> {
        val groups = match.groupValues
        val stageNumber = groups[5].toIntOrNull() ?: Int.MAX_VALUE
        return listOf(groups[1], groups[2], groups[3], groups[4], stageNumber.toString())
    }
}

class VersionFormatException(versionName: String) : Exception(
    "This version is not supported: [$versionName], " +
            "only supported semanthic version " +
            "and stages 'rc', 'alpha' or 'beta'"
)