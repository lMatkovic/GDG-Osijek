import kotlin.random.Random

class Die {
    var value: Int = 1
        private set

    fun roll() {
        value = Random.nextInt(1, 7)
    }
}

class Hand {
    private val dice: List<Die> = List(6) { Die() }
    private val lockedDice: MutableSet<Int> = mutableSetOf()

    fun rollAll() {
        for (i in dice.indices) {
            if (i !in lockedDice) dice[i].roll()
        }
    }

    fun lockDice(indices: List<Int>) {
        lockedDice.clear()
        lockedDice.addAll(indices.filter { it in dice.indices })
    }

    fun getResults(): List<Int> {
        return dice.map { it.value }
    }

    fun checkYahtzee(): Boolean {
        val results = getResults()
        return results.all { it == results[0] }
    }

    fun checkPoker(): Boolean {
        val results = getResults()
        return results.groupingBy { it }.eachCount().values.any { it >= 4 }
    }

    fun checkStraight(): Boolean {
        val results = getResults().sorted()
        return results == listOf(1, 2, 3, 4, 5) || results == listOf(2, 3, 4, 5, 6)
    }
}

fun testYahtzeeGame() {
    val hand = Hand()
    hand.rollAll()
    println("Roll results: ${hand.getResults()}")
    println("Yahtzee: ${hand.checkYahtzee()}")
    println("Poker: ${hand.checkPoker()}")
    println("Straight: ${hand.checkStraight()}")

    hand.lockDice(listOf(0, 1, 2))
    println("Locked first three dice. Rerolling others.")
    hand.rollAll()
    println("Roll results after reroll: ${hand.getResults()}")
}

fun main() {
    testYahtzeeGame()
}
