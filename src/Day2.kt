private const val DAY = 2

fun main() {
    fun part1(input: List<Command>): Int {
        var horizontal = 0
        var vertical = 0
        input.forEach {
            when (it.direction) {
                Direction.FORWARD -> horizontal += it.increment
                Direction.DOWN -> vertical += it.increment
                Direction.UP -> vertical -= it.increment
            }
        }
        return horizontal * vertical
    }

    fun part2(input: List<Command>): Int {
        var horizontal = 0
        var vertical = 0
        var aim = 0
        input.forEach {
            when (it.direction) {
                Direction.FORWARD -> {
                    vertical += aim * it.increment
                    horizontal += it.increment
                }
                Direction.DOWN -> aim +=  it.increment
                Direction.UP -> aim -=  it.increment
            }
        }
        return horizontal * vertical
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true).map { Command(it) }
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput(day = DAY).map { Command(it) }
    println(part1(input))
    println(part2(input))
}

private enum class Direction {
    FORWARD,
    DOWN,
    UP,
}

private class Command(val direction: Direction, val increment: Int)

private fun Command(input: String): Command {
    val inputs = input.split(" ")
    return Command(Direction.valueOf(inputs[0].uppercase()), inputs[1].toInt())
}
