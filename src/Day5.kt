private const val DAY = 5

fun main() {
    fun part1(input: List<Line>): Int {
        val max = input.getMax()
        val matrix = Array(max + 1) { IntArray(max + 1) }

        input.forEach { line ->
            val (x1, y1) = line.from
            val (x2, y2) = line.to
            when {
                x1 == x2 -> matrix.markDangerZoneXEqual(line)
                y1 == y2 -> matrix.markDangerZoneYEqual(line)
            }
        }

        return getDangerZoneCount(matrix)
    }

    fun part2(input: List<Line>): Int {
        val max = input.getMax()
        val matrix = Array(max + 1) { IntArray(max + 1) }

        input.forEach { line ->
            val (x1, y1) = line.from
            val (x2, y2) = line.to
            when {
                x1 == x2 -> matrix.markDangerZoneXEqual(line)
                y1 == y2 -> matrix.markDangerZoneYEqual(line)
                else -> matrix.markDangerZoneDiagonal(line)
            }

        }

        return getDangerZoneCount(matrix)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true).map { Line(it) }

    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput(day = DAY).map { Line(it) }
    println(part1(input))
    println(part2(input))
}

private fun Array<IntArray>.markDangerZoneXEqual(line: Line) {
    var (x1, y1) = line.from
    var y2 = line.to.second

    if (y1 > y2) {
        val tempY = y1
        y1 = y2
        y2 = tempY
    }
    for (y in y1 until y2 + 1) {
        this[y][x1]++
    }
}

private fun Array<IntArray>.markDangerZoneYEqual(line: Line) {
    var (x1, y1) = line.from
    var x2 = line.to.first

    if (x1 > x2) {
        val tempX = x1
        x1 = x2
        x2 = tempX
    }
    for (x in x1 until x2 + 1) {
        this[y1][x]++
    }
}

private fun Array<IntArray>.markDangerZoneDiagonal(line: Line) {
    val (x1, y1) = line.from
    val (x2, y2) = line.to

    val deltaX = x2 - x1
    val deltaY = y2 - y1
    val incrementX = deltaX >= 0
    val incrementY = deltaY >= 0

    var x = x1
    var y = y1
    repeat(kotlin.math.abs(deltaX) + 1) {
        this[y][x]++
        if (incrementX) {
            x++
        } else {
            x--
        }
        if (incrementY) {
            y++
        } else {
            y--
        }
    }
}

private fun getDangerZoneCount(matrix: Array<IntArray>) = matrix.toList().map { it.toList() }.flatten().count { it >= 2 }

private class Line(val from: Pair<Int, Int>, val to: Pair<Int, Int>)

private fun Line(text: String): Line {
    val line = text.replace(" -> ", ",").split(",").map { it.toInt() }
    return Line(from = line[0] to line[1], to = line[2] to line[3])
}

private fun List<Line>.getMax() =
    map {
        maxOf(it.to.first, it.from.first, it.to.second, it.from.second)
    }.maxOf { it }
