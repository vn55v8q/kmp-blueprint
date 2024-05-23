package platform.validators.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class RemoveNumberInStringTest {
    private val sut = RemoveNumberInString
    
    @Test
    fun givenAnStringWithNumber_whenCallRemoveNumberInString_thenReturnStringWhitoutNumber(){
        val name = "hardroid98"
        val nameWhitoutNumber = sut.invoke(name)
        assertEquals("hardroid", nameWhitoutNumber)
    }
}