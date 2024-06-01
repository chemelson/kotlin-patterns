package ru.chemelson.patterns.gof

fun main() {
    val computer = Computer()
    computer.startComputer()
}

class CPU {
    fun initialize() {
        println("CPU init")
    }
}

class Memory {
    fun load(position: Long, data: String) {
        println("Loading $data into memory at position $position.")
    }
}

class HardDrive {
    fun readBootSector(): String {
        return "Boot sector data"
    }
}

class OperatingSystem {
    fun loadKernel(): String {
        return "OS kernel data"
    }
}

// This is actually a Facade
class Computer {
    private val cpu = CPU()
    private val memory = Memory()
    private val hardDrive = HardDrive()
    private val operatingSystem = OperatingSystem()

    fun startComputer() {
        val bootSector = hardDrive.readBootSector()
        val osData = operatingSystem.loadKernel()
        memory.load(0, bootSector)
        memory.load(1024, osData)
        cpu.initialize()
    }
}