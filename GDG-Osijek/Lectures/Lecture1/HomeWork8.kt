fun isStrongPassword(password: String): Boolean {
    if (password.length < 8) return false
    if (!password.any { it.isUpperCase() }) return false
    if (!password.any { it.isDigit() }) return false
    return true
}

fun main() {
    val testPasswords = listOf("Password1", "weak", "12345678", "StrongPass123", "short7", "NoNumber")

    for (password in testPasswords) {
        val result = if (isStrongPassword(password)) "strong" else "weak"
        println("Password '$password' is $result.")
    }
}
