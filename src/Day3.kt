private const val DAY = 3

fun main() {
    fun part1(input: List<String>): Int {
        val countedZeros = IntArray(input[0].length)
        val countedOnes = IntArray(input[0].length)
        for ((x, line) in input.withIndex()) {
            for ((y, _) in line.withIndex()) {
                when (input[x][y]) {
                    '0' -> countedZeros[y]++
                    '1' -> countedOnes[y]++
                }
            }
        }

        val gammaRateInBit = CharArray(countedZeros.size)
        val epsilonRateInBit = CharArray(countedZeros.size)
        for (i in countedZeros.indices) {
            val mostCommonBit: Char
            val leastCommonBit: Char
            if (countedOnes[i] > countedZeros[i]) {
                mostCommonBit = '1'
                leastCommonBit = '0'
            } else {
                mostCommonBit = '0'
                leastCommonBit = '1'
            }
            gammaRateInBit[i] = mostCommonBit
            epsilonRateInBit[i] = leastCommonBit
        }

        return gammaRateInBit.toDecimal() * epsilonRateInBit.toDecimal()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput(day = DAY)
    println(part1(input))
    //println(part2(input))
}

private fun CharArray.toDecimal(): Int {
    return concatToString().toInt(2)
}
