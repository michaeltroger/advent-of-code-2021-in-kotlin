fun main() {
    fun part1(input: List<String>): Int {
        var getsDeeperCount = 0
        input.forEachIndexed { index: Int, currentLine: String ->
            if (index == 0) return@forEachIndexed
            val previousDepth = input[index - 1].toInt()
            val currentDepth = currentLine.toInt()
            if (currentDepth > previousDepth) {
                getsDeeperCount++
            }
        }
        return getsDeeperCount
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
