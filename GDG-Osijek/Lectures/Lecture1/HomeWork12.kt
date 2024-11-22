import kotlin.math.sqrt

class Point2D {

    var x: Double
    var y: Double

    constructor() {
        x = 0.0
        y = 0.0
    }

    constructor(value: Double) {
        x = value
        y = value
    }

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    fun translate(dx: Double, dy: Double) {
        x += dx
        y += dy
    }

    fun distanceTo(other: Point2D): Double {
        val dx = x - other.x
        val dy = y - other.y
        return sqrt(dx * dx + dy * dy)
    }
}

fun main() {
    val defaultPoint = Point2D()
    println("Default Point: (${defaultPoint.x}, ${defaultPoint.y})")

    val equalPoint = Point2D(5.0)
    println("Point with equal coordinates: (${equalPoint.x}, ${equalPoint.y})")

    val customPoint = Point2D(3.0, 4.0)
    println("Custom Point: (${customPoint.x}, ${customPoint.y})")

    customPoint.translate(2.0, 3.0)
    println("Custom Point after translation: (${customPoint.x}, ${customPoint.y})")

    val anotherPoint = Point2D(7.0, 1.0)
    val distance = customPoint.distanceTo(anotherPoint)
    println("Distance between (${customPoint.x}, ${customPoint.y}) and (${anotherPoint.x}, ${anotherPoint.y}) is $distance")
}
