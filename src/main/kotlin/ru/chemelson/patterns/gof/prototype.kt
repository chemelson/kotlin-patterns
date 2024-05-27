package ru.chemelson.patterns.gof

/**
 * Instead of using clumsy clone method, Kotlin provides copy method
 * for data classes. So let's use it.
 */

fun main() {
    val prototype = User(
        name = "default",
        role = "default",
        tasks = listOf("live", "play", "obey") // Pretend this prop is expensive to get
    )

    val john = prototype.copy(
        name = "John",
        role = "Megamind"
    )

    println(john)
}

data class User(
    val name: String,
    val role: String,
    val tasks: List<String>
)