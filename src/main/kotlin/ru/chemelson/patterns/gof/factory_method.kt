package ru.chemelson.patterns.gof

fun main() {
    val factory: ShapeFactory = DefaultShapeFactory()

    val hexagon = factory.getShape("hexagon")
    hexagon.draw()

    val pentagon = factory.getShape("pentagon")
    pentagon.draw()
}

// Absract base class
abstract class Shape {
    abstract fun draw()
}

// Concrete class 1
class Hexagon : Shape() {
    override fun draw() {
        println("Hexagon")
    }
}

// Concrete class 2
class Pentagon : Shape() {
    override fun draw() {
        println("Pentagon")
    }
}

// Factory interface
interface ShapeFactory {
    fun getShape(type: String): Shape
}

// Concrete factory implementation
class DefaultShapeFactory : ShapeFactory {
    override fun getShape(type: String): Shape = when (type) {
        "hexagon" -> Hexagon()
        "pentagon" -> Pentagon()
        else -> throw IllegalArgumentException("unknown type")
    }
}