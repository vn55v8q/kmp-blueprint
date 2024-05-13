package platform.validators.domain

import platform.validators.domain.exception.EmailFormatException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ParseEmailToEntityTest {

    private val sut: ParseEmailToEntity = ParseEmailToEntity()

    @Test
    fun givenToAnEmailWithUnderscoreName_whenCallParseEmailToEntity_thenReturnEmailValue() {
        val email = "harttyn_arce@hotmail.com"
        val emailEntity = sut.parse(email)
        assertEquals(
            emailEntity.name, "harttyn_arce"
        )
        assertEquals(
            emailEntity.domain, "hotmail"
        )
        assertEquals(
            emailEntity.dotCom, "com"
        )
    }

    @Test
    fun givenToAnEmailWithDotName_whenCallParseEmailToEntity_thenReturnEmailValue() {
        val email = "harttyn.arce@hotmail.com"
        val emailEntity = sut.parse(email)
        assertEquals(
            emailEntity.name, "harttyn.arce"
        )
        assertEquals(
            emailEntity.domain, "hotmail"
        )
        assertEquals(
            emailEntity.dotCom, "com"
        )
    }

    @Test
    fun givenToAnEmailWithDotNameEdgeCase_whenCallParseEmailToEntity_thenReturnEmailValue() {
        val email = "harttyn.andres.arce.gajardo@hotmail.com"
        val emailEntity = sut.parse(email)
        assertEquals(
            emailEntity.name, "harttyn.andres.arce.gajardo"
        )
        assertEquals(
            emailEntity.domain, "hotmail"
        )
        assertEquals(
            emailEntity.dotCom, "com"
        )
    }

    @Test
    fun givenToAnEmailWithHyphenName_whenCallParseEmailToEntity_thenReturnEmailValue() {
        val email = "harttyn-arce@gmail.com"
        val emailEntity = sut.parse(email)
        assertEquals(
            emailEntity.name, "harttyn-arce"
        )
        assertEquals(
            emailEntity.domain, "gmail"
        )
        assertEquals(
            emailEntity.dotCom, "com"
        )
    }

    @Test
    fun givenToAnEmailWithHyphenNameEdgeCase_whenCallParseEmailToEntity_thenReturnEmailValue() {
        val email = "harttyn1-andres1-arce2-gajardo3@gmail.com.co"
        val emailEntity = sut.parse(email)
        assertEquals(
            emailEntity.name, "harttyn1-andres1-arce2-gajardo3"
        )
        assertEquals(
            emailEntity.domain, "gmail"
        )
        assertEquals(
            emailEntity.dotCom, "com.co"
        )
    }

    @Test
    fun givenToAnEmailWithErrorFormat_whenCallParseEmailToEntity_thenThrowEmailFormatException() {
        assertFailsWith<EmailFormatException> {
            sut.parse("harttyn@arce@gmail.com")
        }
        assertFailsWith<EmailFormatException> {
            sut.parse("harttyn.arce@gmail.com.com.co")
        }
        assertFailsWith<EmailFormatException> {
            sut.parse("harttyn .arce@gmail.com.com.co")
        }

        assertFailsWith<EmailFormatException> {
            sut.parse("harttyn.arce/gmail.com.com.co")
        }

        assertFailsWith<EmailFormatException> {
            sut.parse("harttyn/arce@gmail.com.com.co")
        }
    }

}