private const val DAY = 8

fun main() {
    fun part1(input: List<String>) =
        input.map {
            it.split(" | ")[1].split(" ")
        }.flatten().count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }

    fun part2(input: List<String>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput(day = DAY)
    println(part1(input))
    println(part2(input))
}

