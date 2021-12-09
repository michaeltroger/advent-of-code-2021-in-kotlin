private const val DAY = 9

fun main() {
    fun part1(input: List<String>) =-1
    fun part2(input: List<String>) = -1

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 15)
    check(part2(testInput) == 61229)

    val input = readInput(day = DAY)
    println(part1(input))
    println(part2(input))
}