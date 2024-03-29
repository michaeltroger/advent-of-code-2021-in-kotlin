private const val DAY = 11

fun main() {
    fun part1(input: List<List<Int>>): Int {
        val octopuses = input.map { it.toIntArray() }.toTypedArray()
        var totalFlashCount = 0
        repeat(100) {
            increaseEnergyLevelOfAllByOne(octopuses)
            flashWhenEnoughEnergy(octopuses)
            totalFlashCount += resetEnergyLevelAfterFlashing(octopuses)
        }
        return totalFlashCount
    }

    fun part2(input: List<List<Int>>): Int {
        val octopuses = input.map { it.toIntArray() }.toTypedArray()
        var totalFlashCount = 0
        var step = 0
        while(true) {
            step++
            increaseEnergyLevelOfAllByOne(octopuses)
            flashWhenEnoughEnergy(octopuses)
            val flashCountInStep = resetEnergyLevelAfterFlashing(octopuses)
            if (flashCountInStep == 100) { // if all are flashing simultaneously
                return step
            }
            totalFlashCount += flashCountInStep
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true).parseInput()
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput(day = DAY).parseInput()
    println(part1(input))
    println(part2(input))
}

private fun resetEnergyLevelAfterFlashing(octopuses: Array<IntArray>): Int {
    var flashCountInStep = 0
    for (x in octopuses.indices) {
        for (y in octopuses[0].indices) {
            if (octopuses[x][y] > 9) {
                octopuses[x][y] = 0
                flashCountInStep++
            }
        }
    }
    return flashCountInStep
}

private fun flashWhenEnoughEnergy(octopuses: Array<IntArray>) {
    val flashedOctopuses: Array<BooleanArray> = Array(10)  { BooleanArray(10) }
    for (x in octopuses.indices) {
        for (y in octopuses[0].indices) {
            if (octopuses[x][y] > 9 && !flashedOctopuses[x][y]) {
                makeOctopusFlash(octopuses, x, y, flashedOctopuses)
            }
        }
    }
}

private fun increaseEnergyLevelOfAllByOne(octopuses: Array<IntArray>) {
    for (x in octopuses.indices) {
        for (y in octopuses[0].indices) {
            octopuses[x][y]++
        }
    }
}

private fun makeOctopusFlash(octopuses: Array<IntArray>, x: Int, y: Int, flashedOctopuses: Array<BooleanArray>) {
    flashedOctopuses[x][y] = true

    if (x > 0) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x-1, y, flashedOctopuses)
    }
    if (x < octopuses.size - 1) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x+1, y, flashedOctopuses)
    }
    if (y > 0) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x, y-1, flashedOctopuses)
    }
    if (y < octopuses[0].size - 1) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x, y+1, flashedOctopuses)
    }

    if (x > 0 && y > 0) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x-1, y-1, flashedOctopuses)
    }
    if (y < octopuses[0].size - 1 && x < octopuses.size - 1) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x+1, y+1, flashedOctopuses)
    }
    if (x < octopuses.size - 1 && y > 0) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x+1, y-1, flashedOctopuses)
    }
    if (y < octopuses.size - 1 && x > 0) {
        increaseEnergyAndFlashIfThresholdReached(octopuses, x-1, y+1, flashedOctopuses)
    }
}

private fun increaseEnergyAndFlashIfThresholdReached(octopuses: Array<IntArray>, xIndex: Int, yIndex: Int, flashedOctopuses: Array<BooleanArray>) {
    octopuses[xIndex][yIndex]++
    if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
        makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
    }
}

private fun List<String>.parseInput(): List<List<Int>> {
    return map { it.map { it.toString().toInt() } }
}
