package llc.redstone.htslreborn.parser

import llc.redstone.htslreborn.tokenizer.Tokenizer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.io.path.Path

class ParserTest {

    @Test
    fun testChangeVelocity() {
        val input = """
            changeVelocity %var.player/t% 1.0 0.0
        """.trimIndent()

        val tokens = Tokenizer.tokenize(input)
        val preProcessedTokens = PreProcess.preProcess(tokens)
        val actions = Parser.parse(preProcessedTokens, Path("test.htsl"))["base"] ?: emptyList()

        assertEquals(1, actions.size, "Should have parsed exactly one action")

        val action = actions[0]
        // Assuming the toString representation is as expected by the user
        // Or we can check properties if we know the class structure
        assertEquals("ChangeVelocity(x=%var.player/t%, y=1.0, z=0.0)", action.toString())
    }

    @Test
    fun testMultipleActions() {
        val input = """
            changeVelocity 1.0 2.0 3.0
            changeVelocity %var.player/x% %var.player/y% %var.player/z%
        """.trimIndent()

        val tokens = Tokenizer.tokenize(input)
        val preProcessedTokens = PreProcess.preProcess(tokens)
        val actions = Parser.parse(preProcessedTokens, Path("test.htsl"))["base"] ?: emptyList()

        assertEquals(2, actions.size)
        assertEquals("ChangeVelocity(x=1.0, y=2.0, z=3.0)", actions[0].toString())
        assertEquals("ChangeVelocity(x=%var.player/x%, y=%var.player/y%, z=%var.player/z%)", actions[1].toString())
    }
}
