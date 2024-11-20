fun main() {
    print("Enter a positive integer (n): ")
    val n = readLine()?.toIntOrNull()

    if (n != null && n > 0) {
        var sum = 0
        for (i in 1..n) {
            sum += i
        }
        println("The sum of the first $n natural numbers is: $sum")
    } else {
        println("Please enter a valid positive integer.")
    }
}
