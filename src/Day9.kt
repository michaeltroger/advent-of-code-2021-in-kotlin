private const val DAY = 9

fun main() {
    fun part1(input: List<List<Point>>): Int {
        var sum = 0
        for (x in input.indices) {
            for (y in input[0].indices) {
                val currentHeight = input[x][y].height
                if (x > 0 && currentHeight >= input[x-1][y].height) {
                    continue
                }
                if (y > 0 && currentHeight >= input[x][y-1].height) {
                    continue
                }
                if (x < input.size - 1 && currentHeight >= input[x+1][y].height) {
                    continue
                }
                if (y < input[0].size - 1 && currentHeight >= input[x][y+1].height) {
                    continue
                }
                sum += currentHeight + 1
            }
        }
        return sum
    }

    fun part2(input: List<List<Point>>): Int {
        val basinSizes = mutableListOf<Int>()
        for (x in input.indices) {
            for (y in input[0].indices) {
                val currentHeight = input[x][y].height
                if (x > 0 && currentHeight >= input[x-1][y].height) {
                    continue
                }
                if (y > 0 && currentHeight >= input[x][y-1].height) {
                    continue
                }
                if (x < input.size - 1 && currentHeight >= input[x+1][y].height) {
                    continue
                }
                if (y < input[0].size - 1 && currentHeight >= input[x][y+1].height) {
                    continue
                }
                val basinSize = getBasinSize(input, x, y) + 1 // plus low point itself
                basinSizes.add(basinSize)
            }
        }
        val biggest = basinSizes.apply {
            sort()
            reverse()
        }.take(3)
        return biggest[0] * biggest[1] * biggest[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput(day = DAY, useTestInput = true))
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = parseInput(readInput(day = DAY))
    println(part1(input))
    println(part2(input))
}

private fun getBasinSize(input: List<List<Point>>, x: Int, y: Int): Int {
    var sum = 0
    val currentHeight = input[x][y].height
    if (x > 0 && currentHeight < input[x-1][y].height && input[x-1][y].height != 9.toByte() && !input[x-1][y].marked) {
        input[x-1][y].marked = true
        sum += getBasinSize(input, x - 1, y) + 1
    }
    if (y > 0 && currentHeight < input[x][y-1].height && input[x][y-1].height != 9.toByte() && !input[x][y-1].marked) {
        input[x][y-1].marked = true
        sum += getBasinSize(input, x, y - 1) + 1
    }
    if (x < input.size - 1 && currentHeight < input[x+1][y].height && input[x+1][y].height != 9.toByte() && !input[x+1][y].marked) {
        input[x+1][y].marked = true
        sum += getBasinSize(input, x + 1, y) + 1
    }
    if (y < input[0].size - 1 && currentHeight < input[x][y+1].height && input[x][y+1].height != 9.toByte() && !input[x][y+1].marked) {
        input[x][y+1].marked = true
        sum += getBasinSize(input, x, y + 1) + 1
    }
    return sum
}

private fun parseInput(input: List<String>): List<List<Point>> {
    return input.map {
        it.map {
            Point(it.toString().toByte())
        }
    }
}

private class Point(val height: Byte, var marked: Boolean = false)