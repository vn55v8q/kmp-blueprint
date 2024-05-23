package platform.validators.domain

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UpdateLocalStringTest {
    private val remoteList = mutableListOf("uno", "dos")
    private val localList = mutableListOf("uno")
    private val fakeBlackListClient = FakeBlackListClient(
        "FakeRemote",
        2,
        remoteList
    )
    private val fakeBlackListSaveableClient = FakeBlackListCacheClient(
        "FakeLocal",
        1,
        localList
    )
    private val sut = UpdateLocalString(
        fakeBlackListClient,
        fakeBlackListSaveableClient
    )

    @Test
    fun givenARemoteVersionMajorToLocalVersion_whenCallUpdateLocalString_thenUpdateLocalValuesForRemoteValues() {
        runTest {
            // Given
            assertEquals(fakeBlackListSaveableClient.version, 1)
            assertEquals(fakeBlackListClient.version, 2)
            // When
            sut.invoke()
            // Then
            assertEquals(fakeBlackListSaveableClient.version, 2)
            assertEquals(
                fakeBlackListClient.mutablaList,
                fakeBlackListSaveableClient.mutablaList
            )
        }
    }
}

class FakeBlackListClient(
    override val id: String,
    override var version: Int,
    override var mutablaList: MutableList<String>

) : BlackListClient<String> {
    constructor(list: List<String>) : this("FakeList", 0, list.toMutableList())

    override suspend fun syncAndGetVersion(): Int {
        return version
    }

    override suspend fun syncData() {}
}

class FakeBlackListCacheClient(
    override val id: String = "FakeList",
    override var version: Int = 0,
    override var mutablaList: MutableList<String> = mutableListOf()
) : BlackListCacheClient<String> {
    constructor(list: List<String>) : this("FakeList", 0, list.toMutableList())

    override suspend fun syncAndGetVersion(): Int {
        return version
    }

    override suspend fun syncData() {}
}