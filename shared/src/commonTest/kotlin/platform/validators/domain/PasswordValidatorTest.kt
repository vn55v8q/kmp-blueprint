package platform.validators.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class PasswordValidatorTest {

    private val validator = PasswordValidator()

    @Test
    fun testInsufficientPassword() {
        val result = validator.invoke("short")
        assertEquals(PasswordStrength.INSUFFICIENT, result)
    }

    @Test
    fun testCommonPassword() {
        val result = validator.invoke("password123")
        assertEquals(PasswordStrength.COMMON, result)
    }

    @Test
    fun testGoodPassword() {
        val result = validator.invoke("Password1")
        assertEquals(PasswordStrength.GOOD, result)
    }

    @Test
    fun testExcellentPassword() {
        val result = validator.invoke("P@ssw0rd1.23456rtert..ert37dfgdfgdfg_er")
        assertEquals(PasswordStrength.EXCELLENT, result)
    }

    @Test
    fun testContainsGoodWord() {
        val result = validator.invoke("IloveYou2!")
        assertEquals(PasswordStrength.GOOD, result)
    }
}