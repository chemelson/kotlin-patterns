package ru.chemelson.patterns.gof

import ru.chemelson.patterns.gof.ParticleType.Companion.getParticleType

fun main() {
    val system = ParticleSystem()
    system.addParticle(
        x = 0f,
        y = 0f,
        velocityX = 1f,
        velocityY = 1f,
        lifespan = 60,
        texture = "SmokeTexture",
        shape = "Circle",
        color = "Gray"
    )
    system.addParticle(
        x = 10f,
        y = 10f,
        velocityX = 2f,
        velocityY = 2f,
        lifespan = 60,
        texture = "SmokeTexture",
        shape = "Circle",
        color = "Gray"
    )
    system.simulate()
}

// This class implements Flyweight itself and FlyweightFactory at once
class ParticleType private constructor(val texture: String, val shape: String, val color: String) {
    companion object {
        private val cache: MutableMap<String, ParticleType> = HashMap()

        fun getParticleType(texture: String, shape: String, color: String): ParticleType? {
            val key = "$texture$shape$color"
            if (!cache.containsKey(key)) {
                cache[key] = ParticleType(texture, shape, color)
            }
            return cache[key]
        }
    }
}

// This class uses Flyweight instances
class Particle(
    private var x: Float,
    private var y: Float,
    private val velocityX: Float,
    private val velocityY: Float,
    private var lifespan: Int,
    private val type: ParticleType?
) {
    fun update() {
        x += velocityX
        y += velocityY
        lifespan--
    }

    fun draw() {
        println("Drawing particle at (" + x + ", " + y + ") with texture: " + type!!.texture)
    }
}

// This is where we get Flyweight instance and inject it
class ParticleSystem {
    private val particles: MutableList<Particle> = ArrayList()

    fun addParticle(
        x: Float,
        y: Float,
        velocityX: Float,
        velocityY: Float,
        lifespan: Int,
        texture: String,
        shape: String,
        color: String
    ) {
        val type = getParticleType(texture, shape, color)
        particles.add(Particle(x, y, velocityX, velocityY, lifespan, type))
    }

    fun simulate() {
        for (particle in particles) {
            particle.update()
            particle.draw()
        }
    }
}
