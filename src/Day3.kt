private const val DAY = 3

fun main() {
    fun part1(input: List<String>): Int {
        val (gammaRateInBit, epsilonRateInBit) = findMostAndLeastCommonBits(input)
        return gammaRateInBit.concatToString().toInt(2) * epsilonRateInBit.concatToString().toInt(2)
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRatingInBit = findRemainingBitNumberRecursively(input, searchMostCommon = true)
        val co2ScrubberRatingInBit = findRemainingBitNumberRecursively(input, searchMostCommon = false)
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

private fun findRemainingBitNumberRecursively(input: List<String>, searchMostCommon: Boolean, index: Int = 0): String {
    if (input.size == 1) {
        return input.first()
    }

    val (mostCommonBit, leastCommonBit) = findMostAndLeastCommonBitAtIndex(input, index)
    val bitToCompare = if (searchMostCommon) {
        mostCommonBit
    } else {
        leastCommonBit
    }
    val newList = mutableListOf<String>()
    input.forEach { bitNumber: String ->
        if (bitNumber[index] == bitToCompare) {
            newList.add(bitNumber)
        }
    }
    return findRemainingBitNumberRecursively(newList, searchMostCommon, index + 1)
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

private fun findMostAndLeastCommonBitAtIndex(input: List<String>, index: Int): Pair<Char, Char> {
    var countedZeros = 0
    var countedOnes = 0
    for ((x, _) in input.withIndex()) {
        when (input[x][index]) {
            '0' -> countedZeros++
            '1' -> countedOnes++
        }
    }

    val mostCommonBit: Char
    val leastCommonBit: Char
    if (countedOnes >= countedZeros) {
        mostCommonBit = '1'
        leastCommonBit = '0'
    } else {
        mostCommonBit = '0'
        leastCommonBit = '1'
    }

    return mostCommonBit to leastCommonBit
}
