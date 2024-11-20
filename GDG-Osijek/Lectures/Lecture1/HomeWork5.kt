fun main() {
    print("Enter the lower bound of the range: ")
    val lowerBound = readLine()?.toIntOrNull()

    print("Enter the upper bound of the range: ")
    val upperBound = readLine()?.toIntOrNull()

    if (lowerBound != null && upperBound != null && lowerBound < upperBound) {
        var validInput = false
        var number: Int

        while (!validInput) {
            print("Enter a number between $lowerBound and $upperBound: ")
            number = readLine()?.toIntOrNull() ?: continue

            if (number in lowerBound..upperBound) {
                validInput = true
                val largestDigit = findLargestDigit(number)
                println("The largest digit in $number is: $largestDigit")
            } else {
                println("Please enter a number within the specified range.")
            }
        }
    } else {
        println("Invalid range. Make sure the lower bound is less than the upper bound.")
    }
}

fun findLargestDigit(number: Int): Int {
    var maxDigit = 0
    var tempNumber = number.absoluteValue

    while (tempNumber > 0) {
        val digit = tempNumber % 10
        if (digit > maxDigit) {
            maxDigit = digit
        }
        tempNumber /= 10
    }
    return maxDigit
}
