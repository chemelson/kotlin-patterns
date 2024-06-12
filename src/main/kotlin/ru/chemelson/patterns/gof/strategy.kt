package ru.chemelson.patterns.gof

fun main() {
    val travelPlan = TravelPlan()

    travelPlan.setTransportationStrategy(airTravel)
    travelPlan.setAccommodationStrategy(hotelStay)
    travelPlan.setActivityStrategy(adventureSports)
    println(travelPlan.generatePlan())

    println("Another plan:")

    // Let's use another strategy for transport
    travelPlan.setTransportationStrategy(trainTravel)
    println(travelPlan.generatePlan())
}

// Strategy Interfaces
typealias TransportationStrategy = () -> String
typealias AccommodationStrategy = () -> String
typealias ActivityStrategy = () -> String

// Concrete Strategies
val airTravel = fun() = "Travel by air: Fast and convenient for long distances."
val trainTravel = fun() = "Travel by train: if you are afraid of planes."
val hotelStay = fun() = "Stay in a hotel: Enjoy comfort and luxury services"
val adventureSports = fun() = "Engage in adventure sports: Exciting and thrilling experiences."

// Context Class: Travel Plan
class TravelPlan {
    private var transportationStrategy: TransportationStrategy? = null
    private var accommodationStrategy: AccommodationStrategy? = null
    private var activityStrategy: ActivityStrategy? = null

    fun setTransportationStrategy(strategy: TransportationStrategy?) {
        this.transportationStrategy = strategy
    }

    fun setAccommodationStrategy(strategy: AccommodationStrategy?) {
        this.accommodationStrategy = strategy
    }

    fun setActivityStrategy(strategy: ActivityStrategy?) {
        this.activityStrategy = strategy
    }

    fun generatePlan(): String {
        val plan = StringBuilder()
        if (transportationStrategy != null) {
            plan.append(transportationStrategy!!.invoke()).append("\n")
        }
        if (accommodationStrategy != null) {
            plan.append(accommodationStrategy!!.invoke()).append("\n")
        }
        if (activityStrategy != null) {
            plan.append(activityStrategy!!.invoke()).append("\n")
        }
        return plan.toString()
    }
}