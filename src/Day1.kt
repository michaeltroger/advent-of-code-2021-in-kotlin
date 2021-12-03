private const val DAY = 1

fun main() {
    fun part1(input: List<Int>): Int {
        var getsDeeperCount = 0
        for (i in 1 until input.size) {
            if (input[i] > input[i - 1]) {
                getsDeeperCount++
            }
        }
        return getsDeeperCount
    }

    fun part2(input: List<Int>): Int {
        var getsDeeperCount = 0
        var previousMeasurement = 0
        for (i in 2 until input.size) {
            val currentMeasurement = input[i - 2] + input[i - 1] + input[i]
            if (previousMeasurement != 0 && currentMeasurement > previousMeasurement) {
                getsDeeperCount++
            }
            previousMeasurement = currentMeasurement
        }
        return getsDeeperCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true).map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput(day = DAY).map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
