private const val DAY = 3

fun main() {
    fun part1(input: List<String>): Int {
        var mostCommonBits = ""
        var leastCommonBits = ""
        for (i in input[0].indices) {
            val (mostCommonBit, leastCommonBit) = findMostAndLeastCommonBit(input, i)
            mostCommonBits += mostCommonBit
            leastCommonBits += leastCommonBit
        }
        return mostCommonBits.toInt(2) * leastCommonBits.toInt(2)
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

    val (mostCommonBit, leastCommonBit) = findMostAndLeastCommonBit(input, index)
    val bitToCompare = if (searchMostCommon) {
        mostCommonBit
    } else {
        leastCommonBit
    }
    val newList = input.filter {
        it[index] == bitToCompare
    }
    return findRemainingBitNumberRecursively(newList, searchMostCommon, index + 1)
}

private fun findMostAndLeastCommonBit(input: List<String>, index: Int): Pair<Char, Char> {
    var countedZeros = 0
    var countedOnes = 0
    for (x in input.indices) {
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
