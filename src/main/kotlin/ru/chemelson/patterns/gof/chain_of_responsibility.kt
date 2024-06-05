package ru.chemelson.patterns.gof

/**
 * I found this example:
 * "As a software architect, I have a unique approach to interaction. Preferring solitude, I often find
 * myself at “The Ivory Tower,” my favorite cafe. Here, I designed a web application to manage
 * developer inquiries. Rather than having them approach me directly, developers must send their
 * questions through this system. I’ll respond if I find their inquiry merits an answer."
 */

fun main() {
    val request = Request(
        email = "developer@company.com",
        question = "Who broke my build?",
    )
    val chain = basicValidation(authentication(finalResponse()))
    val response = chain(request)
    println(response)
}

// Play with methods return values to get different results
data class Request(val email: String, val question: String) {
    fun isKnownEmail() = true
    fun isFromJuniorDeveloper() = true // Set 'false' to get answer from God-like arch
}

data class Response(val answer: String)

typealias Handler = (request: Request) -> Response

val basicValidation = fun(next: Handler) =
    fun(request: Request): Response {
        require(request.email.isNotEmpty()) { "Email must not be empty." }
        require(request.question.isNotEmpty()) { "Question must not be empty." }
        return next(request)
    }

val authentication = fun(next: Handler) =
    fun(request: Request): Response {
        require(request.isKnownEmail()) { "Unknown email address" }
        return next(request)
    }

val finalResponse = fun() =
    fun(request: Request): Response {
        require(!request.isFromJuniorDeveloper()) { "Not for newbies" }
        return Response("This is very complex question. I can't answer it right now.")
    }