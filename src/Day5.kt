private const val DAY = 5

fun main() {
    fun part1(input: List<Line>): Int {
        return -1
    }

    fun part2(input: List<Line>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true).map { Line(it) }

    check(part1(testInput) == 5)
    //check(part2(testInput) == 1924)

    val input = readInput(day = DAY).map { Line(it) }
    println(part1(input))
    //println(part2(input))
}

private class Line(val from: Pair<Int, Int>, val to: Pair<Int, Int>)

private fun Line(text: String): Line {
    val line = text.replace(" -> ", ",").split(",").map { it.toInt() }
    return Line(from = line[0] to line[1], to = line[2] to line[3])
}
