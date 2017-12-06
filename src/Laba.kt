import kotlin.math.abs
import kotlin.math.exp

fun main(args: Array<String>) {

    k = (kRight + kLeft)/2

    var iterations = 0

    do {
        print(k)
        refreshK()
        iterations++
    } while (abs(diff(k)) > acceptDifference)

    print("$iterations iterations")
}

val ERRORS = arrayOf(4, 3, 3, 1, 0, 1, 0)
val TIMES = arrayOf(1, 2, 3, 4, 5, 6, 7)
val acceptDifference = 0.0001
var kLeft = 0.0
var kRight = 1.0
var k = 0.0

fun refreshK() {
    if (diff(k)<0) {
        kRight = k
    } else {
        kLeft = k
    }
    k = (kRight + kLeft) / 2
}

fun diff(k: Double): Double {
    return leftPart(k) - rightPart(k)
}

fun print(k: Double) {
    println("k = $k")
    println("LeftPart = ${leftPart(k)}")
    println("RightPart = ${rightPart(k)}")
    println()
}

fun rightPart(k: Double): Double {
    var a = 0.0
    var b = 0.0
    var c = 0.0

    for (index in 0..6) {
        a += exp(-2 * k * (TIMES[index]))
        b += ERRORS[index] * exp(-1 * k * TIMES[index]) * TIMES[index]
        c += TIMES[index] * exp(-2 * k * TIMES[index])
    }

    return a * (b / c)
}

fun leftPart(k: Double): Double {
    var res = 0.0
    for (index in 0..6) {
        res += ERRORS[index] * exp(-1 * k * TIMES[index])
    }
    return res
}