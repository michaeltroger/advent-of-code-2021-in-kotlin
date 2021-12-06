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

    fun part2(input: List<Byte>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput(day = DAY, useTestInput = true))
    check(part1(testInput) == 5934)
    //check(part2(testInput) == 5)

    val input = parseInput(readInput(day = DAY))
    println(part1(input))
   // println(part2(input))
}

private fun parseInput(input: List<String>) = input.first().split(",").map { it.toByte() }

