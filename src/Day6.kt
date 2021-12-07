private const val DAY = 6

private const val DAYS_TO_SIMULATE = 80

fun main() {
    fun part1(input: List<Byte>): Int {
        val fish = input.toMutableList()

        repeat(DAYS_TO_SIMULATE) {
            val iterate = fish.listIterator()
            while (iterate.hasNext()) {
                when (val internalTimer = iterate.next()) {
                    0.toByte() -> {
                        iterate.set(6)
                        iterate.add(8)
                    } else -> iterate.set((internalTimer - 1).toByte())
                }

            }
        }

        return fish.size
    }

    fun part2(input: List<Byte>): Long {
        var fishCountOnDay = LongArray(9)
        input.forEach {
            fishCountOnDay[it.toInt()]++
        }
        repeat(256) {
            val tempFishCount = LongArray(9)
            fishCountOnDay.forEachIndexed { daysUntilBirth, fishCount ->
                when(daysUntilBirth) {
                    0 -> {
                        tempFishCount[6] += fishCount
                        tempFishCount[8] += fishCount
                    }
                    else -> {
                        tempFishCount[daysUntilBirth - 1] += fishCount
                    }
                }
            }
            fishCountOnDay = tempFishCount

        }
        return fishCountOnDay.toList().sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput(day = DAY, useTestInput = true))
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = parseInput(readInput(day = DAY))
    println(part1(input))
    println(part2(input))
}

private fun parseInput(input: List<String>) = input.first().split(",").map { it.toByte() }

