import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int, useTestInput: Boolean = false): List<String> {
    var filename = "Day$day"
    if (useTestInput) {
        filename += "_test"
    }
    filename += ".txt"
    return File("src/input", filename).readLines()
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
