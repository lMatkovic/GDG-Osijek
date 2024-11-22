import kotlin.math.PI

class Circle(private val radius: Double) {

    init {
        require(radius >= 0) { "Radius must be non-negative" }
    }

    fun area(): Double {
        return PI * radius * radius
    }

    fun circumference(): Double {
        return 2 * PI * radius
    }

    companion object {
        fun unitCircle(): Circle {
            return Circle(1.0)
        }
    }
}

fun main() {
    val unitCircle = Circle.unitCircle()
    println("Unit Circle -> Radius: 1.0, Area: ${unitCircle.area()}, Circumference: ${unitCircle.circumference()}")

    val customCircle = Circle(5.0)
    println("Custom Circle -> Radius: 5.0, Area: ${customCircle.area()}, Circumference: ${customCircle.circumference()}")

    try {
        val invalidCircle = Circle(-3.0)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
    }
}
