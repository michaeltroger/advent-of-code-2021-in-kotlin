private const val DAY = 6

fun main() {
    fun getFishPopulationCount(initialState: List<Byte>, daysToSimulate: Int): Long {
        var fishCountPerDay = LongArray(9)
        initialState.forEach {
            fishCountPerDay[it.toInt()]++
        }
        repeat(daysToSimulate) {
            val tempFishCount = LongArray(9)
            fishCountPerDay.forEachIndexed { daysUntilBirth, fishCount ->
                when(daysUntilBirth) {
                    0 -> {
                        tempFishCount[6] += fishCount
                        tempFishCount[8] += fishCount
                    }
                    else -> tempFishCount[daysUntilBirth - 1] += fishCount
                }
            }
            fishCountPerDay = tempFishCount
        }
        return fishCountPerDay.sum()
    }

    fun part1(input: List<Byte>) = getFishPopulationCount(input, 80)
    fun part2(input: List<Byte>) = getFishPopulationCount(input, 256)

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput(day = DAY, useTestInput = true))
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539)

    val input = parseInput(readInput(day = DAY))
    println(part1(input))
    println(part2(input))
}

private fun parseInput(input: List<String>) = input.first().split(",").map { it.toByte() }

