package platform.log

import io.github.aakira.napier.Napier

object Log {
    fun d(className: String, message: String) {
        Napier.d(message, tag = className)
    }
}