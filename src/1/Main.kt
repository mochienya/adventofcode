import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val input = File("src/1/input.txt").readText()
    val lines = input.split("\n")
    var array1 = arrayListOf<Int>()
    var array2 = arrayListOf<Int>()
    for (line in lines) {
        val intPair = line.trim().split("\\s+".toRegex()).map { it.toInt() }
        array1.add(intPair[0])
        array2.add(intPair[1])
    }
    array1.sort()
    array2.sort()

    var result: Int = 0

    for (i in array1.indices) {
        val localResult = array1[i] - array2[i]
        result += localResult.absoluteValue
    }
    println(result)

    val occurAmnt = mutableMapOf<Int, Int>()
    for (num in array2) {
        if (occurAmnt.contains(num)) {
            occurAmnt[num] = occurAmnt[num]!! + 1
        } else {
            occurAmnt[num] = 1
        }
    }

    var result2: Int = 0

    for (i in array1) {
        if (array2.contains(i)) {
            result2 += i * occurAmnt[i]!!
        }
    }
    println(result2)
}