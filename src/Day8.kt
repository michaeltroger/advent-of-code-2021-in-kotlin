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

            val one = map[2]!!.first()
            val four = map[4]!!.first()
            val seven = map[3]!!.first()
            val eight = map[7]!!.first()

            val digitsWith5Segments = map[5]!!.toMutableList()
            // 3 =
            val threeIndex = digitsWith5Segments.indexOfFirst { it.contains(one[0]) && it.contains(one[1]) }
            val three = digitsWith5Segments.removeAt(threeIndex)

            // 5 =
            val fiveHint = four.replace(one[0].toString(), "").replace(one[1].toString(), "")
            val fiveIndex = digitsWith5Segments.indexOfFirst { it.contains(fiveHint[0]) && it.contains(fiveHint[1]) }
            val five = digitsWith5Segments.removeAt(fiveIndex)

            // 2 =
            val two = digitsWith5Segments.first()

            val digitsWith6Segments = map[6]!!.toMutableList()
            // 6 =
            val sixIndex = digitsWith6Segments.indexOfFirst { it.contains(one[0]) xor it.contains(one[1]) }
            val six = digitsWith6Segments.removeAt(sixIndex)

            // 0
            val zeroHint = eight
                .replace(four[0].toString(), "")
                .replace(four[1].toString(), "")
                .replace(four[2].toString(), "")
                .replace(four[3].toString(), "")
                .replace(seven[0].toString(), "")
                .replace(seven[1].toString(), "")
                .replace(seven[2].toString(), "")
            val zeroIndex = digitsWith6Segments.indexOfFirst { it.contains(zeroHint[0]) && it.contains(zeroHint[1]) }
            val zero = digitsWith6Segments.removeAt(zeroIndex)

            // 9 =
            val nine = digitsWith6Segments.first()

            val output = it[1].map {
                    when (it.toCharArray().sorted().joinToString("")) {
                        zero.toCharArray().sorted().joinToString("") -> 0
                        one.toCharArray().sorted().joinToString("") -> 1
                        two.toCharArray().sorted().joinToString("") -> 2
                        three.toCharArray().sorted().joinToString("") -> 3
                        four.toCharArray().sorted().joinToString("") -> 4
                        five.toCharArray().sorted().joinToString("") -> 5
                        six.toCharArray().sorted().joinToString("") -> 6
                        seven.toCharArray().sorted().joinToString("") -> 7
                        eight.toCharArray().sorted().joinToString("") -> 8
                        nine.toCharArray().sorted().joinToString("") -> 9
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

