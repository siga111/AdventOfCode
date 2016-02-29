package days.week3

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 */
class TestDay14 {

    val SIMPLE_ANSWER = "2696"
    val ADVANCED_ANSWER = "1084"

    @Test
    fun testCalculate() {
        assertEquals(SIMPLE_ANSWER, Day14().calculate())
    }

    @Test
    fun testCalculateAdvanced() {
        assertEquals(ADVANCED_ANSWER, Day14().calculateAdvanced())
    }
}