package y2015

import core.AbstractDay
import core.extensions.md5Hex

class Day4(input: List<String>) : AbstractDay(input) {

    override fun calculate(): String = inputFirstLine
        .firstsMd5ThatStartsWith("00000")
        .toString()

    override fun calculateAdvanced(): String = inputFirstLine
        .firstsMd5ThatStartsWith("000000")
        .toString()

    private fun String.firstsMd5ThatStartsWith(resultPrefix: String): Int {
        var suffix = 1
        while (!"%s%d".format(this, suffix).md5Hex().startsWith(resultPrefix)) {
            suffix++
        }
        return suffix
    }
}