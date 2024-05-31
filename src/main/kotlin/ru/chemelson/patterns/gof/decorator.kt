package ru.chemelson.patterns.gof

fun main() {
    var pizza: Pizza = PlainPizza()
    pizza = CheeseDecorator(pizza)
    pizza = PepperoniDecorator(pizza)
    pizza = PineappleDecorator(pizza)

    println("Cost: " + pizza.cost)
    println("Description: " + pizza.description)
}

// Abstract component
interface Pizza {
    val cost: Double
    val description: String
}

// Concrete component
class PlainPizza : Pizza {
    override val cost: Double
        get() = 10.0

    override val description: String
        get() = "Plain Pizza"
}

// First decorator
class CheeseDecorator(private val pizza: Pizza) : Pizza {
    override val cost: Double
        get() = pizza.cost + 2.5

    override val description: String
        get() = "${pizza.description}, Cheese"
}

// Second decorator
class PepperoniDecorator(private val pizza: Pizza) : Pizza {
    override val cost: Double
        get() = pizza.cost + 3

    override val description: String
        get() = "${pizza.description}, Pepperoni"
}

// Third decorator - pay attention to delegation
class PineappleDecorator(private val pizza: Pizza) : Pizza by pizza {
    override val description: String
        get() = "${pizza.description}, Pineapple"
}