import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("src/2/input.txt").readText()
    val reports = input.split("\n")
    val repArrArray = mutableListOf<List<Int>>() //damn daniel
    for (report in reports) {
        val repArr = report.trim().split("\\s+".toRegex()).map { it.toInt() } // me when the same line works on 2 days
        repArrArray += repArr
    }
    var (safeAmnt, safeAmnt2) = listOf<Int>(0, 0) //I HATE YOU I HATE YOU I HATE YOU i want my int var, var2 = value
    for (report in repArrArray) {
        if (isSafe(report)) {safeAmnt++}
        if (isSafe2(report)) {safeAmnt2++}
    }
    println("$safeAmnt\n$safeAmnt2")
}

fun isSafe(report: List<Int>): Boolean {
    var prevNum: Int = -1
    var isIncreasing: Boolean? = null
    for (num in report) {
        if (prevNum == -1) { // dumb thing for less indentation
            prevNum = num
            continue
        }

        if (abs(num - prevNum) > 3) {return false}

        if (num == prevNum) {return false}

        if (isIncreasing == null) { isIncreasing = num > prevNum }
        if (isIncreasing) {
            if (num < prevNum) {return false}
        } else {
            if (num > prevNum) {return false}
        }

        prevNum = num
    }
    return true
}

fun isSafe2(report: List<Int>): Boolean {
    if (isSafe(report)) {return true}
    for (reportIndex in report.indices) {
        val tempReport = report.filterIndexed { index, _ -> (index != reportIndex) } // workaround for filter { it.indexOf() }
        if (isSafe(tempReport)) {return true}
    }
    return false
}
