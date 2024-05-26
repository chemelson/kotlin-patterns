package ru.chemelson.patterns.gof

/**
 * While it is possible to implement "classic" builder, in Kotlin
 * it is easier to use language features like 'apply' scope function
 * or named arguments
 */

fun main() {
    val email = Email(listOf("john@doe.com")).apply {
        message = "Hello there"
        title = "Well, hello"
        important = true
    }
    println(email)

    println()

    val spamEmail = SpamEmail(
        to = listOf("john@doe.com"),
        title = "Bye",
        message = "Bye bye"
    )
    println(spamEmail)
}

// Class for apply. It's ok, but lacks Kotlin excellent null-safety features
data class Email(
    val to: List<String>,
    var message: String? = null,
    var cc: List<String>? = null,
    var title: String? = null,
    var important: Boolean? = null,
)

// Class for defaults and named args.
data class SpamEmail(
    val to: List<String>,
    val message: String = "",
    val cc: List<String> = listOf(),
    val title: String = "",
    val important: Boolean = false,
)