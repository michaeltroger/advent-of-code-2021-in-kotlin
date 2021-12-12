private const val DAY = 10

fun main() {
    fun part1(input: List<String>): Int {
        var syntaxErrorScore = 0
        input.map { line ->
            val expectedCloseSigns = mutableListOf<Char>()
            line.forEach { actualChar ->
                when(actualChar) {
                    '[' -> expectedCloseSigns.add(']')
                    '(' -> expectedCloseSigns.add(')')
                    '{' -> expectedCloseSigns.add('}')
                    '<' -> expectedCloseSigns.add('>')
                    ']', ')', '}', '>' -> {
                        if (expectedCloseSigns.removeLast() != actualChar) {
                            syntaxErrorScore += getSyntaxErrorPoints(actualChar)
                            return@forEach
                        }
                    }
                }
            }
        }

        return syntaxErrorScore
    }

    fun part2(input: List<String>): Long {
        val totalScoresLines = mutableListOf<Long>()
        val incompleteLines = input.removeCorruptedLines()
        incompleteLines.map { line ->
            val expectedCloseSigns = mutableListOf<Char>()
            line.forEach { actualChar ->
                when(actualChar) {
                    '[' -> expectedCloseSigns.add(']')
                    '(' -> expectedCloseSigns.add(')')
                    '{' -> expectedCloseSigns.add('}')
                    '<' -> expectedCloseSigns.add('>')
                    ']', ')', '}', '>' -> expectedCloseSigns.removeLast()
                }
            }
            var totalScore = 0L
            expectedCloseSigns.reversed().forEach { char ->
                totalScore *= 5
                totalScore += getAutocompletePoints(char)
            }
            totalScoresLines.add(totalScore)
        }

        return totalScoresLines.sorted()[totalScoresLines.size / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput(day = DAY)
    println(part1(input))
    println(part2(input))
}

private fun List<String>.removeCorruptedLines(): List<String> {
    return filter { line ->
        val expectedCloseSigns = mutableListOf<Char>()
        var isLegal = true
        line.forEach { actualChar ->
            when(actualChar) {
                '[' -> expectedCloseSigns.add(']')
                '(' -> expectedCloseSigns.add(')')
                '{' -> expectedCloseSigns.add('}')
                '<' -> expectedCloseSigns.add('>')
                ']', ')', '}', '>' -> {
                    if (expectedCloseSigns.removeLast() != actualChar) {
                        isLegal = false
                        return@forEach
                    }
                }
            }
        }
        isLegal
    }
}

private fun getSyntaxErrorPoints(char: Char) = when (char) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> throw Error("unexpected char")
}

private fun getAutocompletePoints(char: Char) = when (char) {
    ')' -> 1
    ']' -> 2
    '}' -> 3
    '>' -> 4
    else -> throw Error("unexpected char")
}