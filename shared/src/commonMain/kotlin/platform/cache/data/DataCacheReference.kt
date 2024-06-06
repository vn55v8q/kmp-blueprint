package platform.cache.data

import com.russhwolf.settings.Settings
import platform.cache.domain.CacheReference

class DataCacheReference(
    private val settings: Settings
) : CacheReference {
    override fun putInt(key: String, value: Int) {
        settings.putInt(key, value)
    }

    override fun putBoolean(key: String, value: Boolean) {
        settings.putBoolean(key, value)
    }

    override fun putString(key: String, value: String) {
        settings.putString(key, value)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return settings.getInt(key, defaultValue)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return settings.getBoolean(key, defaultValue)
    }

    override fun getString(key: String, defaultValue: String): String {
        return settings.getString(key, defaultValue)
    }
}