package ru.chemelson.patterns.gof

fun main() {
    val doc = Document()
    doc.publish()
    doc.approve()
}

// State interface
sealed interface State {
    fun publish(doc: Document)
    fun approve(doc: Document)
}

// Context
class Document {
    private var state: State

    init {
        this.state = Draft
    }

    fun setState(state: State) {
        this.state = state
    }

    fun publish() {
        state.publish(this)
    }

    fun approve() {
        state.approve(this)
    }
}

// Concrete states
data object Draft : State {
    override fun publish(doc: Document) {
        println("Publishing draft, moving to moderation")
        doc.setState(Moderation)
    }

    override fun approve(doc: Document) {
        println("Draft cannot be approved directly.")
    }
}

data object Moderation : State {
    override fun publish(doc: Document) {
        println("Cannot publish from Moderation without approval.")
    }

    override fun approve(doc: Document) {
        println("Approving moderation, moving to published.")
        doc.setState(Published)
    }
}

data object Published : State {
    override fun publish(doc: Document) {
        println("Already published.")
    }

    override fun approve(doc: Document) {
        println("Already approved.")
    }
}