import java.io.File

fun main() {
    val input = File("src/3/input.txt").readText()

    println("${part1(input)}")
    println("${part2(input)}")
}

fun part1(input: String): Int {
    return Regex("""mul\((\d+),(\d+)\)""").findAll(input)
        .map { match -> match.groupValues.slice(1..2).map{ it.toInt() } }
        .map { it[0] * it[1] }.sum()
}

fun part2(input: String): Int {
    val operators = Regex("""do\(\)|don't\(\)|mul\(\d+,\d+\)""").findAll(input).map { it.groupValues[0] }
    var isMult: Boolean = true

    var result: Int = 0
    for(operator in operators) {
        if(isMult && operator.startsWith("mul")) {
            val list = "(\\d+),(\\d+)".toRegex().find(operator)!!.destructured.toList().map { it.toInt() }
            result += list[0] * list[1]
            continue
        }
        isMult = operator == "do()"

    }
    return result
}