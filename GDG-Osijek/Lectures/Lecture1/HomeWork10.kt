fun countUniqueCharacters(input: String): Int {
    return input.toSet().size
}

fun main() {
    val testStrings = listOf("Hello", "Kotlin", "Programming", "123456", "aaaaaa", "")

    for (string in testStrings) {
        val count = countUniqueCharacters(string)
        println("The string '$string' contains $count unique character(s).")
    }
}
