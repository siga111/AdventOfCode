package days.week3

import days.week3.Day12
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 */
class TestDay12 {

    val SIMPLE_ANSWER = "0"
    val ADVANCED_ANSWER = "0"

    @Test
    fun testCalculate() {
        assertEquals(SIMPLE_ANSWER, Day12().calculate())
    }

    @Test
    fun testCalculateAdvanced() {
        assertEquals(ADVANCED_ANSWER, Day12().calculateAdvanced())
    }
}