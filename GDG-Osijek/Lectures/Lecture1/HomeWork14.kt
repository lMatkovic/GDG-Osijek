enum class Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

enum class Rank(val value: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14)
}

data class Card(val suit: Suit, val rank: Rank)

class Deck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        shuffle()
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun dealOneCard(): Card? {
        return if (cards.isNotEmpty()) cards.removeAt(0) else null
    }
}

class WarGame {
    private val deck = Deck()
    private var playerOneScore = 0
    private var playerTwoScore = 0

    fun playRound(): String {
        val playerOneCard = deck.dealOneCard()
        val playerTwoCard = deck.dealOneCard()

        if (playerOneCard == null || playerTwoCard == null) {
            return "Game over! Final scores: Player 1: $playerOneScore, Player 2: $playerTwoScore"
        }

        val result = when {
            playerOneCard.rank.value > playerTwoCard.rank.value -> {
                playerOneScore++
                "Player 1 wins the round with ${playerOneCard.rank} of ${playerOneCard.suit} against ${playerTwoCard.rank} of ${playerTwoCard.suit}."
            }
            playerTwoCard.rank.value > playerOneCard.rank.value -> {
                playerTwoScore++
                "Player 2 wins the round with ${playerTwoCard.rank} of ${playerTwoCard.suit} against ${playerOneCard.rank} of ${playerOneCard.suit}."
            }
            else -> {
                "It's a tie with ${playerOneCard.rank} of ${playerOneCard.suit} and ${playerTwoCard.rank} of ${playerTwoCard.suit}."
            }
        }
        return result
    }

    fun playGame() {
        println("Starting War game!")
        while (true) {
            val roundResult = playRound()
            println(roundResult)
            if (roundResult.contains("Game over")) break
        }
    }
}

fun main() {
    val game = WarGame()
    game.playGame()
}
