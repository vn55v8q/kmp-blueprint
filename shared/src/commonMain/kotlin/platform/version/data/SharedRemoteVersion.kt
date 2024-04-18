package platform.version.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import platform.version.data.response.VersionResponse
import platform.version.domain.RemoteVersion
import platform.version.domain.entity.VersionApp

class SharedRemoteVersion : RemoteVersion {
    private val url = "https://vn55v8q.github.io/screen-orchestra-repository/version.json"
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun getVersionApp(): VersionApp {
        try {
            val httpResponse: HttpResponse = client.get(url)
            val versionBody: VersionResponse = httpResponse.body()
            val version = versionBody.version
            return VersionApp(version.stable, version.min)
        } catch (e: Exception){
            return VersionApp.empty()
        }
    }

}