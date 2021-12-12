private const val DAY = 11
fun main() {
    fun part1(octopuses: Array<IntArray>): Int {
        var flashCount = 0
        repeat(100) {
            // increase energy level of all by one
            for (x in octopuses.indices) {
                for (y in octopuses[0].indices) {
                    octopuses[x][y]++
                }
            }
            // flash all octopuses with energy > 9 plus adjacent ones
            val flashedOctopuses: Array<BooleanArray> = Array(10)  { BooleanArray(10) }
            for (x in octopuses.indices) {
                for (y in octopuses[0].indices) {
                    if (octopuses[x][y] > 9 && !flashedOctopuses[x][y]) {
                        //octopuses[x][y]++
                        makeOctopusFlash(octopuses, x, y, flashedOctopuses)
                    }
                }
            }
            // reset energy level
            for (x in octopuses.indices) {
                for (y in octopuses[0].indices) {
                    if (octopuses[x][y] > 9) {
                        octopuses[x][y] = 0
                        flashCount++
                    }
                }
            }
        }
        return flashCount
    }

    fun part2(octopuses: Array<IntArray>): Int {
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true).toIntArray()
    check(part1(testInput) == 1656)
    //check(part2(testInput) == 288957)

    val input = readInput(day = DAY).toIntArray()
    println(part1(input))
    println(part2(input))
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

private fun List<String>.toIntArray(): Array<IntArray> {
    return map { it.toCharArray().map { it.toString().toInt() }.toIntArray() }.toTypedArray()
}
