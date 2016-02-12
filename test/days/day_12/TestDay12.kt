package days.day_12

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 */
class TestDay12 {

    val SIMPLE_ANSWER = "0"
    val ADVANCED_ANSWER = "0"

    @Test
    fun testCalculate() {
        assertEquals(SIMPLE_ANSWER, Day().calculate())
    }

    @Test
    fun testCalculateAdvanced() {
        assertEquals(ADVANCED_ANSWER, Day().calculateAdvanced())
    }
}