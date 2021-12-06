private const val DAY = 5

fun main() {
    fun part1(input: List<Line>): Int {
        val max = input.getMax()

        val array = Array(max + 1) { IntArray(max + 1) }
        input.forEach { line ->
            var (x1, y1) = line.from
            var (x2, y2) = line.to
            if (x1 == x2) {
                if (y1 > y2) {
                    val tempY = y1
                    y1 = y2
                    y2 = tempY
                }
                for (y in y1 until y2 + 1) {
                    array[y][x1]++
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    val tempX = x1
                    x1 = x2
                    x2 = tempX
                }
                for (x in x1 until x2 + 1) {
                    array[y1][x]++
                }
            }
        }

        return array.toList().map { it.toList() }.flatten().count { it >= 2 }
    }

    fun part2(input: List<Line>): Int {
        val max = input.getMax()

        val array = Array(max + 1) { IntArray(max + 1) }
        input.forEach { line ->
            var (x1, y1) = line.from
            var (x2, y2) = line.to
            if (x1 == x2) {
                if (y1 > y2) {
                    val tempY = y1
                    y1 = y2
                    y2 = tempY
                }
                for (y in y1 until y2 + 1) {
                    array[y][x1]++
                }
            } else if (y1 == y2) {
                if (x1 > x2) {
                    val tempX = x1
                    x1 = x2
                    x2 = tempX
                }
                for (x in x1 until x2 + 1) {
                    array[y1][x]++
                }
            } else {
                val deltaX = x2 - x1
                val deltaY = y2 - y1

                val incrementX = deltaX >= 0
                val incrementY = deltaY >= 0

                var x = x1
                var y = y1
                for (i in 0..kotlin.math.abs(deltaX)) {
                    array[y][x]++
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

        }

        return array.toList().map { it.toList() }.flatten().count { it >= 2 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true).map { Line(it) }

    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput(day = DAY).map { Line(it) }
    println(part1(input))
    println(part2(input))
}

private class Line(val from: Pair<Int, Int>, val to: Pair<Int, Int>)

private fun Line(text: String): Line {
    val line = text.replace(" -> ", ",").split(",").map { it.toInt() }
    return Line(from = line[0] to line[1], to = line[2] to line[3])
}

private fun List<Line>.getMax(): Int {
    var max = 0
    forEach {
        if (it.to.first > max) {
            max = it.to.first
        }
        if (it.from.first > max) {
            max = it.from.first
        }
        if (it.to.second > max) {
            max = it.to.second
        }
        if (it.from.second > max) {
            max = it.from.second
        }
    }
    return max
}
