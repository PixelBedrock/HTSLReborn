package llc.redstone.htslreborn.tokenizer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class TokenizerTest {

    @Test
    fun testNumbersWithoutCommas() {
        val tokens = Tokenizer.tokenize("1234")
        val numberTokens = tokens.filter { it.tokenType == Tokens.INT }
        assertEquals(1, numberTokens.size, "Should have exactly one INT token for 1234")
        assertEquals("1234", numberTokens[0].string)
    }

    @Test
    fun testNumbersWithCommas() {
        val tokens = Tokenizer.tokenize("1,234")
        val numberTokens = tokens.filter { it.tokenType == Tokens.INT }
        assertEquals(1, numberTokens.size, "Should have exactly one INT token for 1,234")
        assertEquals("1,234", numberTokens[0].string)
    }

    @Test
    fun testNegativeNumbers() {
        val tokens = Tokenizer.tokenize("-1234")
        val numberTokens = tokens.filter { it.tokenType == Tokens.INT }
        assertEquals(1, numberTokens.size, "Should have exactly one INT token for -1234")
        assertEquals("-1234", numberTokens[0].string)
    }

    @Test
    fun testLargeNumbersWithMultipleCommas() {
        val tokens = Tokenizer.tokenize("1,234,567")
        val numberTokens = tokens.filter { it.tokenType == Tokens.INT }
        assertEquals(1, numberTokens.size, "Should have exactly one INT token for 1,234,567")
        assertEquals("1,234,567", numberTokens[0].string)
    }

    @Test
    fun testLongs() {
        val tokens = Tokenizer.tokenize("1234L")
        val numberTokens = tokens.filter { it.tokenType == Tokens.LONG }
        assertEquals(1, numberTokens.size, "Should have exactly one LONG token for 1234L")
        assertEquals("1234L", numberTokens[0].string)
    }

    @Test
    fun testDoubles() {
        val tokens = Tokenizer.tokenize("1234.56")
        val numberTokens = tokens.filter { it.tokenType == Tokens.DOUBLE }
        assertEquals(1, numberTokens.size, "Should have exactly one DOUBLE token for 1234.56")
        assertEquals("1234.56", numberTokens[0].string)
    }
}
