package ru.chemelson.patterns.gof

fun main() {
    val manual: Transmission = ManualTransmission()
    val car: Vehicle = Car(manual)
    car.applyTransmission()

    val auto: Transmission = AutomaticTransmission()
    val truck: Vehicle = Truck(auto)
    truck.applyTransmission()
}

// Implementor
interface Transmission {
    fun applyGear()
}

// Concrete implementors
class ManualTransmission : Transmission {
    override fun applyGear() {
        println("Manual transmission applied")
    }
}

class AutomaticTransmission : Transmission {
    override fun applyGear() {
        println("Automatic transmission applied")
    }
}

// Abstraction
abstract class Vehicle(protected var transmission: Transmission) { // <<-- this is the "bridge" itself
    abstract fun applyTransmission()
}

// Refined abstractions
class Car(transmission: Transmission) : Vehicle(transmission) {
    override fun applyTransmission() {
        transmission.applyGear()
    }
}

class Truck(transmission: Transmission) : Vehicle(transmission) {
    override fun applyTransmission() {
        transmission.applyGear()
    }
}