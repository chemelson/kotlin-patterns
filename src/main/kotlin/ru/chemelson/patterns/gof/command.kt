package ru.chemelson.patterns.gof

fun main() {
    val light = Light()
    val turnOnCommand = turnOn(light)
    val turnOffCommand = turnOff(light)

    // Let's continue without invoker
    turnOnCommand.invoke()
    turnOffCommand.invoke()
}

// Command interface
// Let's make it function
typealias Command = () -> Unit

// Receiver - Light
class Light {
    fun turnOn() {
        println("Light is ON!")
    }

    fun turnOff() {
        println("Light is OFF!")
    }
}

// Concrete commands
val turnOn = fun(receiver: Light) =
    fun() = receiver.turnOn()

val turnOff = fun(receiver: Light) =
    fun() = receiver.turnOff()
