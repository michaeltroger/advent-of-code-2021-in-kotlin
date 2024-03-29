private const val DAY = 4

fun main() {
    fun part1(input: Input): Int {
        val randomNumbers: List<Byte> = input.randomNumbers
        val boards: List<Board> = input.bingoBoards
        randomNumbers.forEach { drawnNumber ->
            boards.markNumber(drawnNumber)

            boards.forEach { board ->
                board.forEachIndexed { x, rows ->
                    val totalMarkedInX = rows.count { it.marked }
                    val totalMarkedInY = board.mapIndexed { _, list -> list[x] }.count { it.marked }
                    if (totalMarkedInX == 5 || totalMarkedInY == 5) {
                        return board.findSumOfUnmarkedFields() * drawnNumber
                    }
                }
            }
        }

        return -1
    }

    fun part2(input: Input): Int {
        val randomNumbers: List<Byte> = input.randomNumbers
        val boards: MutableList<Board> = input.bingoBoards.toMutableList()
        val boardsToRemove: MutableList<Board> = mutableListOf()
        var lastWinningBoard: Board? = null
        var lastDrawnNumberWhenWinning: Byte = -1

        randomNumbers.forEach { drawnNumber ->
            boards.markNumber(drawnNumber)

            boards.forEach { board ->
                board.forEachIndexed { x, rows ->
                    val totalMarkedInX = rows.count { it.marked }
                    val totalMarkedInY = board.mapIndexed { _, list -> list[x] }.count { it.marked }
                    if (totalMarkedInX == 5 || totalMarkedInY == 5) {
                        boardsToRemove.add(board)
                        lastWinningBoard = board
                        lastDrawnNumberWhenWinning = drawnNumber
                        return@forEachIndexed
                    }
                }
            }
            if (boardsToRemove.isNotEmpty()) {
                boards.removeAll(boardsToRemove)
                boardsToRemove.clear()
            }
        }

        return lastWinningBoard!!.findSumOfUnmarkedFields() * lastDrawnNumberWhenWinning
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput(day = DAY, useTestInput = true))

    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = parseInput(readInput(day = DAY))
    println(part1(input))
    println(part2(input))
}

private fun List<Board>.markNumber(drawnNumber: Byte) {
    forEach { board ->
        board.forEach { row ->
            row.filter { it.number == drawnNumber }.forEach { field -> field.marked = true }
        }
    }
}

private fun Board.findSumOfUnmarkedFields(): Int = flatten().filter { !it.marked }.map { it.number }.sum()

private fun parseInput(text: List<String>): Input {
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
        }.chunked(5)
        .toList()
    return Input(randomNumbers, bingoBoards)
}

private class Input(val randomNumbers: List<Byte>, val bingoBoards: List<Board>)
private class Field(val number: Byte, var marked: Boolean = false)
private typealias Board = List<List<Field>>