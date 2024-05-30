package ru.chemelson.patterns.gof

fun main() {
    val developmentDepartment = Department("Development")
    val marketingDepartment = Department("Marketing")

    val john = Employee("John", 40)
    val jane = Employee("Jane", 35)
    val mike = Employee("Mike", 30)

    developmentDepartment.addComponent(john)
    developmentDepartment.addComponent(jane)
    marketingDepartment.addComponent(mike)

    println("Total Hours in Development Department: " + developmentDepartment.hours)
    println("Total Hours in Marketing Department: " + marketingDepartment.hours)
}

// Abstract component which defines interface
interface OrganizationComponent {
    val name: String
    val hours: Int
}

// Leaf component
class Employee(override val name: String, override val hours: Int) : OrganizationComponent

// Composite component
class Department(override val name: String) : OrganizationComponent {
    private val components: MutableList<OrganizationComponent> = mutableListOf()
    override val hours: Int
        get() = components.sumOf { it.hours }

    fun addComponent(component: OrganizationComponent) {
        components.add(component)
    }
}