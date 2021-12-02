private const val DAY = 1

fun main() {
    fun part1(input: List<Int>): Int {
        var getsDeeperCount = 0
        input.forEachIndexed { index: Int, currentDepth: Int ->
            if (index == 0) return@forEachIndexed
            val previousDepth = input[index - 1]
            if (currentDepth > previousDepth) {
                getsDeeperCount++
            }
        }
        return getsDeeperCount
    }

    fun part2(input: List<Int>): Int {
        var getsDeeperCount = 0
        var previousMeasurement = 0
        input.forEachIndexed { index: Int, currentInput: Int ->
            if (index < 2) return@forEachIndexed
            val currentMeasurement = input[index - 2] + input[index - 1] + currentInput
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
