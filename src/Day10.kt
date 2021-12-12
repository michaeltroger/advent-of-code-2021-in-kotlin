private const val DAY = 10

fun main() {
    fun part1(input: List<String>): Int {
        var syntaxErrorScore = 0
        input.mapIndexed { lineNumber, line ->
            val expectedCloseSigns = mutableListOf<Char>()
            line.forEach { actualChar ->
                when(actualChar) {
                    '[' -> expectedCloseSigns.add(']')
                    '(' -> expectedCloseSigns.add(')')
                    '{' -> expectedCloseSigns.add('}')
                    '<' -> expectedCloseSigns.add('>')
                    ']', ')', '}', '>' -> {
                        val expected = expectedCloseSigns.removeLast()
                        if (expected != actualChar) {
                            syntaxErrorScore += getSyntaxErrorPoints(actualChar)
                            return@forEach
                        }
                    }
                }
            }
        }

        return syntaxErrorScore
    }

    fun part2(input: List<String>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 26397)
    //check(part2(testInput) == 1134)

    val input = readInput(day = DAY)
    println(part1(input))
    println(part2(input))
}

private fun getSyntaxErrorPoints(char: Char) = when (char) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> throw Error("unexpected char")
}