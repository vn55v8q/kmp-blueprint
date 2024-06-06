package platform.cache.domain

interface CacheReference {
    fun putInt(key: String, value: Int)
    fun putBoolean(key: String, value: Boolean)
    fun putString(key: String, string: String)

    fun getInt(key: String, defaultValue: Int) : Int
    fun getBoolean(key: String, defaultValue: Boolean) : Boolean
    fun getString(key: String, defaultValue: String) : String
}