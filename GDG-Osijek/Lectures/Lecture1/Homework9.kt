fun countVowels(input: String): Int {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    return input.count { it in vowels }
}

fun main() {
    val testStrings = listOf("Hello", "Kotlin Programming", "Vowels", "123456", "AEIOUaeiou", "Sky")

    for (string in testStrings) {
        val count = countVowels(string)
        println("The string '$string' contains $count vowel(s).")
    }
}
