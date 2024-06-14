package ru.chemelson.patterns.gof

fun main() {
    val food: Product = Food()
    val clothing: Product = Clothing()
    val electronics: Product = Electronics()

    val holidayVisitor: DiscountVisitor = HolidayDiscountVisitor()
    val clearanceVisitor: DiscountVisitor = ClearanceDiscountVisitor()

    food.accept(holidayVisitor)
    clothing.accept(clearanceVisitor)
    electronics.accept(holidayVisitor)
}

interface DiscountVisitor {
    fun visitFood(food: Food)
    fun visitClothing(clothing: Clothing)
    fun visitElectronics(electronics: Electronics)
}

interface Product {
    fun accept(visitor: DiscountVisitor)
}

class Food : Product {
    override fun accept(visitor: DiscountVisitor) {
        visitor.visitFood(this)
    }
}

class Clothing : Product {
    override fun accept(visitor: DiscountVisitor) {
        visitor.visitClothing(this)
    }
}

class Electronics : Product {
    override fun accept(visitor: DiscountVisitor) {
        visitor.visitElectronics(this)
    }
}

class HolidayDiscountVisitor : DiscountVisitor {
    override fun visitFood(food: Food) {
        println("Applying holiday discount to food.")
    }

    override fun visitClothing(clothing: Clothing) {
        println("Applying holiday discount to clothing.")
    }

    override fun visitElectronics(electronics: Electronics) {
        println("Applying holiday discount to electronics.")
    }
}

class ClearanceDiscountVisitor : DiscountVisitor {
    override fun visitFood(food: Food) {
        println("Applying clearance discount to food.")
    }

    override fun visitClothing(clothing: Clothing) {
        println("Applying clearance discount to clothing.")
    }

    override fun visitElectronics(electronics: Electronics) {
        println("Applying clearance discount to electronics.")
    }
}