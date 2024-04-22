package platform.version.domain

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IsMinVersionSupportedTest {

    private val sut = IsMinVersion

    @Test
    fun givenACurrenVersionEqualsToMinVersion_whenCallIsMinVersionSupported_thenInvokeFunctionReturnTrue() {
        assertTrue(sut.invoke("1.0.0-alpha01", "1.0.0-alpha01"))
    }

    @Test
    fun givenACurrenVersionMinorToMinVersionSupported_whenCallIsMinVersionSupported_thenInvokeFunctionReturnFalse() {
        assertFalse(sut.invoke("1.0.0-rc01", "1.0.0"))
        assertFalse(sut.invoke("1.0.0-alpha01", "1.0.0-alpha02"))
        assertFalse(sut.invoke("1.0.0", "1.0.1"))
        assertFalse(sut.invoke("1.0.0-alpha01", "1.0.0-beta01"))
        assertFalse(sut.invoke("1.0.0-alpha01", "1.0.0-rc01"))
        assertFalse(sut.invoke("1.0.0-beta01", "1.0.0-rc01"))
    }

    @Test
    fun givenACurrenVersionMayotToCompareVersion_whenCallIsCompareVersion_thenInvokeFunctionReturnTrue() {
        assertTrue(sut.invoke("1.0.0-alpha11", "1.0.0-alpha02"))
        assertTrue(sut.invoke("1.0.0-rc11", "1.0.0-rc02"))
        assertTrue(sut.invoke("1.0.0-rc01", "0.9.0"))
        assertTrue(sut.invoke("1.1.0", "1.0.1"))
        assertTrue(sut.invoke("1.0.0-rc01", "1.0.0-beta01"))
        assertTrue(sut.invoke("1.0.0-beta01", "1.0.0-alpha99"))
        assertTrue(sut.invoke("1.0.0", "1.0.0"))
    }
    
    @Test
    fun givenAIllegalCurrenVersionMinor_whenCallIsMinVersionSupported_thenThrowsVersionFormatException() {
        assertFailsWith<VersionFormatException> {
            sut.invoke("1.0.0-xx01", "1.0.0")
        }
        assertFailsWith<VersionFormatException> {
            sut.invoke("1.0.0", "1.0.0.1")
        }
        assertFailsWith<VersionFormatException> {
            sut.invoke("1.0.0-rc01", "1.0.0-xx99")
        }
    }
    
}