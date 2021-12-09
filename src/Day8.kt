private const val DAY = 8

fun main() {
    fun part1(input: List<String>) =
        input.map {
            it.split(" | ")[1].split(" ")
        }.flatten().count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }

    fun part2(input: List<String>): Int {
        val parsedInput = input.map {
            it.split(" | ").map { it.split(" ") }
        }

        var sum = 0
        parsedInput.forEach {
            val map = it.first().groupBy { it.length }.toSortedMap()

            val segments = Array(10) { "" }

            // 1, 4, 7, 8 have unique segment count
            segments[1] = map[2]!!.first().sortAlphabetically()
            segments[4] = map[4]!!.first().sortAlphabetically()
            segments[7] = map[3]!!.first().sortAlphabetically()
            segments[8] = map[7]!!.first().sortAlphabetically()

            val digitsWith5Segments = map[5]!!.toMutableList() // 2, 3, 5 have 5 segments

            // 3 =
            val threeIndex = digitsWith5Segments.indexOfFirst { it.contains(segments[1][0]) && it.contains(segments[1][1]) }
            segments[3] = digitsWith5Segments.removeAt(threeIndex).sortAlphabetically()

            // 5 =
            val fiveHint = segments[4].replace(segments[1][0].toString(), "").replace(segments[1][1].toString(), "")
            val fiveIndex = digitsWith5Segments.indexOfFirst { it.contains(fiveHint[0]) && it.contains(fiveHint[1]) }
            segments[5] = digitsWith5Segments.removeAt(fiveIndex).sortAlphabetically()

            // 2 =
            segments[2] = digitsWith5Segments.first().sortAlphabetically()

            val digitsWith6Segments = map[6]!!.toMutableList() // 0, 6, 9 have 6 segments

            // 6 =
            val sixIndex = digitsWith6Segments.indexOfFirst { it.contains(segments[1][0]) xor it.contains(segments[1][1]) }
            segments[6] = digitsWith6Segments.removeAt(sixIndex).sortAlphabetically()

            // 0
            val zeroHint = segments[8]
                .replace(segments[4][0].toString(), "")
                .replace(segments[4][1].toString(), "")
                .replace(segments[4][2].toString(), "")
                .replace(segments[4][3].toString(), "")
                .replace(segments[7][0].toString(), "")
                .replace(segments[7][1].toString(), "")
                .replace(segments[7][2].toString(), "")
            val zeroIndex = digitsWith6Segments.indexOfFirst { it.contains(zeroHint[0]) && it.contains(zeroHint[1]) }
            segments[0] = digitsWith6Segments.removeAt(zeroIndex).sortAlphabetically()

            // 9 =
            segments[9] = digitsWith6Segments.first().sortAlphabetically()

            val output = it[1].map {
                    when (it.sortAlphabetically()) {
                        segments[0] -> 0
                        segments[1] -> 1
                        segments[2] -> 2
                        segments[3] -> 3
                        segments[4] -> 4
                        segments[5] -> 5
                        segments[6] -> 6
                        segments[7] -> 7
                        segments[8] -> 8
                        segments[9] -> 9
                        else -> throw Error()
                    }.toString()

            }
            sum += output.joinToString("").toInt()
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput(day = DAY, useTestInput = true)
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput(day = DAY)
    println(part1(input))
    println(part2(input))
}

private fun String.sortAlphabetically() = toCharArray().sorted().joinToString("")

