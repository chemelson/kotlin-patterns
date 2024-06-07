package ru.chemelson.patterns.gof

fun main() {
    val collection = BookCollection();
    collection.addBook(Book("book1"))
    collection.addBook(Book("book2"))
    collection.addBook(Book("book3"))

    for (book in collection) {
        println(book.title)
    }
}

data class Book(val title: String)

class BookCollection {
    private val books: MutableList<Book> = mutableListOf()

    fun addBook(book: Book) {
        books.add(book)
    }

    fun get(index: Int): Book {
        return books[index]
    }

    fun size(): Int {
        return books.size
    }

    // Here's iterator
    operator fun iterator() = object : Iterator<Book> {
        private var index = 0

        override fun hasNext() = index < size()

        override fun next() = books[index++]
    }
}