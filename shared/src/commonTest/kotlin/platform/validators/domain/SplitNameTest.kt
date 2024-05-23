package platform.validators.domain

import kotlin.test.Test
import kotlin.test.assertEquals

class SplitNameTest {
    private val sut = SplitName

    @Test
    fun givenToAnCorrectName_whenCallToSplitFunction_thenReturnListWithNames() {
        val names = "harttin.andres.arce.gajardo"
        val splitNames = sut.invoke(names)
        assertEquals(splitNames.size, 4)
        assertEquals("harttin", splitNames[0])
        assertEquals("andres", splitNames[1])
        assertEquals("arce", splitNames[2])
        assertEquals("gajardo", splitNames[3])
    }

    @Test
    fun givenToAnCorrectNameWithUnderscore_whenCallToSplitFunction_thenReturnListWithNames() {
        val names = "harttin_andres_arce_gajardo"
        val splitNames = sut.invoke(names)
        assertEquals(splitNames.size, 4)
        assertEquals("harttin", splitNames[0])
        assertEquals("andres", splitNames[1])
        assertEquals("arce", splitNames[2])
        assertEquals("gajardo", splitNames[3])
    }

    @Test
    fun givenToAnCorrectNameWithHyphen_whenCallToSplitFunction_thenReturnListWithNames() {
        val names = "harttin-andres-arce-gajardo"
        val splitNames = sut.invoke(names)
        assertEquals(splitNames.size, 4)
        assertEquals("harttin", splitNames[0])
        assertEquals("andres", splitNames[1])
        assertEquals("arce", splitNames[2])
        assertEquals("gajardo", splitNames[3])
    }

    @Test
    fun givenToAnCorrectNameWithEdgeCase_whenCallToSplitFunction_thenReturnListWithNames() {
        val names = "harttin.andres-arce_gajardo"
        val splitNames = sut.invoke(names)
        assertEquals(splitNames.size, 4)
        assertEquals("harttin", splitNames[0])
        assertEquals("andres", splitNames[1])
        assertEquals("arce", splitNames[2])
        assertEquals("gajardo", splitNames[3])
    }

    @Test
    fun givenToAnCorrectNameWithEdgeCaseSlash_whenCallToSplitFunction_thenReturnListWithNames() {
        val names = "harttin/andres/arce/gajardo"
        val splitNames = sut.invoke(names)
        assertEquals(splitNames.size, 4)
        assertEquals("harttin", splitNames[0])
        assertEquals("andres", splitNames[1])
        assertEquals("arce", splitNames[2])
        assertEquals("gajardo", splitNames[3])
    }

    @Test
    fun givenToAnCorrectNameWithSpaceCase_whenCallToSplitFunction_thenReturnListWithNames() {
        val names = "harttin98 andres1 arce2 gajardo3"
        val splitNames = sut.invoke(names)
        assertEquals(splitNames.size, 4)
        assertEquals("harttin98", splitNames[0])
        assertEquals("andres1", splitNames[1])
        assertEquals("arce2", splitNames[2])
        assertEquals("gajardo3", splitNames[3])
    }
}