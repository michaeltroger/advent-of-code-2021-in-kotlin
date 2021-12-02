private enum class Direction {
    FORWARD,
    DOWN,
    UP,
}

private class Command(val direction: Direction, val increment: Int)

private fun Command(input: String): Command {
    val inputs = input.split(" ")
    return Command(Direction.valueOf(inputs[0].toUpperCase()), inputs[1].toInt())
}

fun main() {
    fun part1(input: List<Command>): Long {
        var horizontal = 0
        var vertical = 0
        input.forEach {
            when (it.direction) {
                Direction.FORWARD -> horizontal += it.increment
                Direction.DOWN -> vertical += it.increment
                Direction.UP -> vertical -= it.increment
            }
        }
        return horizontal.toLong() * vertical
    }

    fun part2(input: List<Command>): Long {
        return 3L
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test").map { Command(it) }
    check(part1(testInput) == 150L)
//    check(part2(testInput) == 150L)

    val input = readInput("Day02").map { Command(it) }
    println(part1(input))
    println(part2(input))
}
