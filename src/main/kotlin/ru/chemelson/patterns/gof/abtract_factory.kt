package ru.chemelson.patterns.gof

fun main() {
    // You can also combine this pattern with Factory Method to get
    // concrete factories instead of initializing them manually

    val coffeeFactory: CafeFactory = CoffeeCafeFactory()
    val coffee = coffeeFactory.createDrink()
    val croissant = coffeeFactory.createPastry()
    coffee.serve()
    croissant.serve()

    val teaFactory: CafeFactory = TeaCafeFactory()
    val tea = teaFactory.createDrink()
    val scone = teaFactory.createPastry()
    tea.serve()
    scone.serve()
}

// Abstract product interfaces
interface Drink {
    fun serve()
}

interface Pastry {
    fun serve()
}

// Concrete products
class CoffeeDrink : Drink {
    override fun serve() {
        println("Serving Coffee")
    }
}

class CoffeePastry : Pastry {
    override fun serve() {
        println("Serving Croissant")
    }
}

class TeaDrink : Drink {
    override fun serve() {
        println("Serving Tea")
    }
}

class TeaPastry : Pastry {
    override fun serve() {
        println("Serving Scone")
    }
}

// Abstract factory interface
interface CafeFactory {
    fun createDrink(): Drink

    fun createPastry(): Pastry
}

// Concrete factories
class CoffeeCafeFactory : CafeFactory {
    override fun createDrink(): Drink {
        return CoffeeDrink()
    }

    override fun createPastry(): Pastry {
        return CoffeePastry()
    }
}

class TeaCafeFactory : CafeFactory {
    override fun createDrink(): Drink {
        return TeaDrink()
    }

    override fun createPastry(): Pastry {
        return TeaPastry()
    }
}
