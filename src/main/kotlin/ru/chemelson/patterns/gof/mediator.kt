package ru.chemelson.patterns.gof

fun main() {
    val mediator = ConcreteMediator()
    val first = FirstComponent()
    val second = SecondComponent()

    mediator.setFirstComponent(first)
    mediator.setSecondComponent(second)

    first.setMediator(mediator)
    second.setMediator(mediator)

    first.sendMessage("Hola!")
}

interface Mediator {
    fun sendMessage(message: String, component: Component)
}

interface Component {
    fun setMediator(mediator: Mediator)
    fun sendMessage(message: String)
    fun receiveMessage(message: String)
}

class FirstComponent : Component {
    private var mediator: Mediator? = null

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    override fun sendMessage(message: String) {
        mediator!!.sendMessage(message, this)
    }

    override fun receiveMessage(message: String) {
        println("First component received: $message")
    }
}

class SecondComponent : Component {
    private var mediator: Mediator? = null

    override fun setMediator(mediator: Mediator) {
        this.mediator = mediator
    }

    override fun sendMessage(message: String) {
        mediator!!.sendMessage(message, this)
    }

    override fun receiveMessage(message: String) {
        println("Second component received: $message")
    }
}

class ConcreteMediator : Mediator {
    private var firstComponent: FirstComponent? = null
    private var secondComponent: SecondComponent? = null

    fun setFirstComponent(firstComponent: FirstComponent) {
        this.firstComponent = firstComponent
    }

    fun setSecondComponent(secondComponent: SecondComponent) {
        this.secondComponent = secondComponent
    }

    override fun sendMessage(message: String, component: Component) {
        if (component == firstComponent && secondComponent != null) {
            secondComponent!!.receiveMessage(message)
        } else if (component == secondComponent && firstComponent != null) {
            firstComponent!!.receiveMessage(message)
        }
    }
}