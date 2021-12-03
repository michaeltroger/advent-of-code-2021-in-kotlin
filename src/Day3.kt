private const val DAY = 3

fun main() {
    fun part1(input: List<String>): Int {
        val (gammaRateInBit, epsilonRateInBit) = findMostAndLeastCommonBits(input)
        return gammaRateInBit.toDecimal() * epsilonRateInBit.toDecimal()
    }

    fun findOxygenGeneratorRating(input: List<String>, index: Int = 0): String {
        if (input.size == 1) {
            return input.first()
        }
        val (mostCommonBits, leastCommonBits) = findMostAndLeastCommonBits(input)
        val newList = mutableListOf<String>()
        input.forEach { bitNumber: String ->
            if (bitNumber[index] == mostCommonBits[index]) {
                newList.add(bitNumber)
            }
        }
        return findOxygenGeneratorRating(newList, index + 1)
    }

    fun findCo2ScrubberRating(input: List<String>, index: Int = 0): String {
        if (input.size == 1) {
            return input.first()
        }
        val (mostCommonBits, leastCommonBits) = findMostAndLeastCommonBits(input)
        val newList = mutableListOf<String>()
        input.forEach { bitNumber: String ->
            if (bitNumber[index] == leastCommonBits[index]) {
                newList.add(bitNumber)
            }
        }
        return findCo2ScrubberRating(newList, index + 1)
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRatingInBit = findOxygenGeneratorRating(input)
        val co2ScrubberRatingInBit = findCo2ScrubberRating(input)
        return oxygenGeneratorRatingInBit.toInt(2) * co2ScrubberRatingInBit.toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput(day = DAY)
    println(part1(input))
    println(part2(input))
}

private fun findMostAndLeastCommonBits(input: List<String>): Pair<CharArray, CharArray> {
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

    val mostCommonBits = CharArray(countedZeros.size)
    val leastCommonBits = CharArray(countedZeros.size)
    for (i in countedZeros.indices) {
        val mostCommonBit: Char
        val leastCommonBit: Char
        if (countedOnes[i] >= countedZeros[i]) {
            mostCommonBit = '1'
            leastCommonBit = '0'
        } else {
            mostCommonBit = '0'
            leastCommonBit = '1'
        }
        mostCommonBits[i] = mostCommonBit
        leastCommonBits[i] = leastCommonBit
    }
    return mostCommonBits to leastCommonBits
}

private fun CharArray.toDecimal(): Int {
    return concatToString().toInt(2)
}
