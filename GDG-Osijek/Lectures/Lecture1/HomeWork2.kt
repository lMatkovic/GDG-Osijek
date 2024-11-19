fun main() {

    print("Enter the first number: ")
    val number1 = readLine()!!.toInt()

    print("Enter the second number: ")
    val number2 = readLine()!!.toInt()

    print("Enter the third number: ")
    val number3 = readLine()!!.toInt()


    val largestNumber = maxOf(number1, number2, number3)


    println("The largest number is: $largestNumber")
}
