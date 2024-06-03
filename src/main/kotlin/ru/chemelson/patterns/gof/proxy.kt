package ru.chemelson.patterns.gof

fun main() {
    val query: DatabaseQuery = CachedDatabaseQuery()
    println(query.executeQuery("SELECT * FROM users")) // Executes and caches
    println(query.executeQuery("SELECT * FROM users")) // Returns cached result
}

// Subject interface Client will use
interface DatabaseQuery {
    fun executeQuery(query: String): String
}

// Delegate of Proxy
class RealDatabaseQuery : DatabaseQuery {
    override fun executeQuery(query: String): String {
        println("Executing database query: $query")
        return "Result of $query"
    }
}

// Proxy
class CachedDatabaseQuery : DatabaseQuery {
    private var delegate: RealDatabaseQuery? = null
    private val cache: MutableMap<String, String> = HashMap()

    override fun executeQuery(query: String): String {
        if (cache.containsKey(query)) {
            println("Returning cached result for query: $query")
            return cache.getValue(key = query)
        } else {
            // This is not thread-safe implementation
            if (delegate == null) {
                delegate = RealDatabaseQuery()
            }
            val result = delegate!!.executeQuery(query)
            cache[query] = result
            return result
        }
    }
}