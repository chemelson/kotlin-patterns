package ru.chemelson.patterns.gof

fun main() {
    val weatherStation = WeatherStation()
    val display = CurrentConditionsDisplay(weatherStation)
    weatherStation.setMeasurements(30.0f, 65f, 1013.1f)
}

interface Observer {
    fun update(temperature: Float, humidity: Float, pressure: Float)
}

interface Subject {
    fun registerObserver(o: Observer)
    fun removeObserver(o: Observer)
    fun notifyObservers()
}

class WeatherStation : Subject {
    private val observers: MutableList<Observer> = mutableListOf()
    private var temperature = 0f
    private var humidity = 0f
    private var pressure = 0f

    override fun registerObserver(o: Observer) {
        observers.add(o)
    }

    override fun removeObserver(o: Observer) {
        observers.remove(o)
    }

    override fun notifyObservers() {
        for (observer: Observer in observers) {
            observer.update(temperature, humidity, pressure)
        }
    }

    fun measurementsChanged() {
        notifyObservers()
    }

    fun setMeasurements(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        measurementsChanged()
    }
}

class CurrentConditionsDisplay(private val weatherStation: Subject) : Observer {
    init {
        weatherStation.registerObserver(this)
    }

    override fun update(temperature: Float, humidity: Float, pressure: Float) {
        println(
            "Current conditions: " + temperature + "F degrees and "
                    + humidity + "% humidity"
        )
    }
}