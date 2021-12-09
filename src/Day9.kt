private const val DAY = 9

fun main() {
    fun part1(input: List<List<Byte>>): Int {
        var sum = 0
        for (x in input.indices) {
            for (y in input[0].indices) {
                val currentHeight = input[x][y]
                if (x > 0 && currentHeight >= input[x-1][y]) {
                    continue
                }
                if (y > 0 && currentHeight >= input[x][y-1]) {
                    continue
                }
                if (x < input.size - 1 && currentHeight >= input[x+1][y]) {
                    continue
                }
                if (y < input[0].size - 1 && currentHeight >= input[x][y+1]) {
                    continue
                }
                sum += currentHeight + 1
            }
        }
        return sum
    }
    fun part2(input: List<List<Byte>>) = -1

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput(day = DAY, useTestInput = true))
    check(part1(testInput) == 15)
  //  check(part2(testInput) == 61229)

    val input = parseInput(readInput(day = DAY))
    println(part1(input))
    println(part2(input))
}

private fun parseInput(input: List<String>): List<List<Byte>> {
    return input.map {
        it.map {
            it.toString().toByte()
        }
    }
}