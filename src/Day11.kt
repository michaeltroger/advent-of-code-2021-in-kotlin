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
    val testInput = readInput(day = DAY, useTestInput = true).toIntArray()
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput(day = DAY).toIntArray()
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

    var xIndex = x-1
    var yIndex = y
    if (x > 0) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }
    xIndex = x+1
    yIndex = y
    if (x < octopuses.size - 1) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }
    xIndex = x
    yIndex = y-1
    if (y > 0) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }
    xIndex = x
    yIndex = y+1
    if (y < octopuses[0].size - 1) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }

    xIndex = x-1
    yIndex = y-1
    if (x > 0 && y > 0) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }
    xIndex = x+1
    yIndex = y+1
    if (y < octopuses[0].size - 1 && x < octopuses.size - 1) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }
    xIndex = x+1
    yIndex = y-1
    if (x < octopuses.size - 1 && y > 0) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }
    xIndex = x-1
    yIndex = y+1
    if (y < octopuses.size - 1 && x > 0) {
        octopuses[xIndex][yIndex]++
        if (octopuses[xIndex][yIndex] > 9 && !flashedOctopuses[xIndex][yIndex]) {
            makeOctopusFlash(octopuses, xIndex, yIndex, flashedOctopuses)
        }
    }
}

private fun List<String>.toIntArray(): List<List<Int>> {
    return map { it.map { it.toString().toInt() } }
}
