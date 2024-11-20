fun isPrime(number: Int): Boolean {
    if (number <= 1) return false
    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

fun main() {
    print("Enter a number: ")
    val number = readLine()?.toIntOrNull() ?: return

    if (isPrime(number)) {
        println("$number is a prime number.")
    } else {
        println("$number is not a prime number.")
    }
}
