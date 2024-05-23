package platform.validators.data

import platform.validators.domain.BlackListCacheClient

class LocalDotComBlackListCache : BlackListCacheClient<String> {
    override val id: String = "local-dot-com-client"
    override var version: Int = 0
    override var mutablaList: MutableList<String> = mutableListOf()
    override suspend fun syncAndGetVersion(): Int {
        // TODO: Crear la lectura en la base de datos
        return version
    }

    override suspend fun syncData(){
        // Esta funcion no es necesario en esta interfaz, aplica segregación de interfaces mi chang, en otro momento si
    }
}
