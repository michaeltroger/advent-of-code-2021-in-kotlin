private const val DAY = 7

fun main() {
    fun part1(input: List<String>) = 0
    fun part2(input: List<String>) = 0

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 37)
    //check(part2(testInput) == 12)

    val input = readInput(day = DAY)
    println(part1(input))
    //println(part2(input))
}
