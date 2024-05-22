package ru.chemelson.patterns.gof

/**
 * Although it is possible to use enum for singleton object (like in Java),
 * Kotlin has native feature for this.
 */

fun main() {
    val instance = Singleton
    println(instance.doSome())
}

object Singleton {
    fun doSome() = "That's all folks!"
}

