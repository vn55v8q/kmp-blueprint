package platform.validators.domain

interface BlackListClient<T> {
    val id: String
    var version: Int
    var mutablaList: MutableList<T>
    
    suspend fun getBlackList(): List<T> {
        return mutablaList
    }

    /**
     * Esta función obtiene la version actual y la retorna para el caso de uso
     * */
    suspend fun syncAndGetVersion() : Int

    suspend fun syncData()
}