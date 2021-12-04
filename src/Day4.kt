private const val DAY = 4

fun main() {
    fun part1(input: Input): Int {
        val randomNumbers: List<Byte> = input.randomNumbers
        val boards: List<Board> = input.bingoBoards
        randomNumbers.forEach { drawnNumber ->
            // mark fields
            boards.forEach { board ->
                board.numbers.forEach { row ->
                    row.filter { it.number == drawnNumber }.forEach { field -> field.marked = true }
                }
            }

            // check if row/column complete
            boards.forEach { board ->
                board.numbers.forEachIndexed { x, rows ->
                    val totalMarkedInX = rows.count { it.marked }
                    val totalMarkedInY = board.numbers.mapIndexed { _, list -> list[x] }.count { it.marked }
                    if (totalMarkedInX == 5 || totalMarkedInY == 5) {
                        val sum = board.numbers.flatten().filter { !it.marked }.map { it.number }.sum()
                        return sum * drawnNumber
                    }
                }
            }
        }

        return -1
    }

    fun part2(input: Input): Int {
        println(input.randomNumbers.size)
        println(input.bingoBoards.size)
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = toInput(readInput(day = DAY, useTestInput = true))

    check(part1(testInput) == 4512)
    //check(part2(testInput) == 230)

    val input = toInput(readInput(day = DAY))
    println(part1(input))
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
                Field(it.toByte())
            }
        }.chunked(5).map {
            Board(it)
        }.toList()
    return Input(randomNumbers, bingoBoards)
}

private class Input(val randomNumbers: List<Byte>, val bingoBoards: List<Board>)
private class Field(val number: Byte, var marked: Boolean = false)
private class Board(val numbers: List<List<Field>>)