fun power(base: Int, exponent: Int): Int {
    var result = 1
    for (i in 1..exponent) {
        result *= base
    }
    return result
}

fun main() {
    print("Enter the base number: ")
    val base = readLine()?.toIntOrNull() ?: return

    print("Enter the exponent: ")
    val exponent = readLine()?.toIntOrNull() ?: return

    val result = power(base, exponent)
    println("$base raised to the power of $exponent is: $result")
}
