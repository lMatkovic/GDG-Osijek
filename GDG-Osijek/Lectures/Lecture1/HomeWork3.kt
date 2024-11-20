fun main() {
    print("Enter a character: ")
    val char = readLine()?.lowercase()?.get(0)

    when (char) {
        'a', 'e', 'i', 'o', 'u' -> println("The entered character is a vowel.")
        else -> println("The entered character is not a vowel.")
    }
}
