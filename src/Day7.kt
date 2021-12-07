import kotlin.math.absoluteValue

private const val DAY = 7

fun main() {
    fun part1(input: List<Int>): Int {
        val max = input.maxOf { it }
        var totalFuelCosts = Int.MAX_VALUE
        for (position in 0..max) {
            input.sumOf { (position - it).absoluteValue }
                .takeIf { it < totalFuelCosts }
                ?.let { totalFuelCosts = it }
        }
        return totalFuelCosts
    }
    fun part2(input: List<Int>): Int {
        val max = input.maxOf { it }
        var totalFuelCosts = Int.MAX_VALUE
        for (position in 0..max) {
            input.sumOf {
                val stepsToMove = (position - it).absoluteValue
                stepsToMove * (stepsToMove + 1) / 2 // Little Gauss formula
            }.takeIf { it < totalFuelCosts }?.let { totalFuelCosts = it }
        }
        return totalFuelCosts
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput(day = DAY, useTestInput = true))
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = parseInput(readInput(day = DAY))
    println(part1(input))
    println(part2(input))
}

private fun parseInput(input: List<String>) = input.first().split(",").map { it.toInt() }
