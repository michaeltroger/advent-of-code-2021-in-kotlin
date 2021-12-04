private const val DAY = 4

fun main() {
    fun part1(input: Input): Int {
        println(input.randomNumbers.size)
        println(input.bingoBoards.size)
        return 0
    }

    fun part2(input: Input): Int {
        println(input.randomNumbers.size)
        println(input.bingoBoards.size)
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = toInput(readInput(day = DAY, useTestInput = true))

    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = toInput(readInput(day = DAY))
    //println(part1(input))
    //println(part2(input))
}

private fun toInput(text: List<String>): Input {
    val randomNumbers: List<Byte> = text.first().split(",").map { it.toByte() }
    val bingoBoards = text.subList(2, text.size)
        .asSequence()
        .filter { it != "" }
        .map {
            if (it.startsWith(" ")) {
                it.removePrefix(" ")
            } else {
                it
            }
        }.map {
            it.replace("  ", " ")
        }.map { row ->
            row.split(" ").map {
                it.toByte()
            }
        }.chunked(5)
        .toList()
    return Input(randomNumbers, bingoBoards)
}

private class Input(val randomNumbers: List<Byte>, val bingoBoards: List<List<List<Byte>>>)