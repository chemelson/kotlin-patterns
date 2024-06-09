package ru.chemelson.patterns.gof

import java.util.*

fun main() {
    val gameManager = GameManager()
    val game = gameManager.newGame()
    game.printStatistics()
    game.play(1, 5)
    game.printStatistics()
    gameManager.makeSave()
    game.play(1, 10)
    game.printStatistics()
    gameManager.loadLastSave()
    game.printStatistics()
}

// Memento - aka snapshot
data class GameSave(val level: Int, val score: Int)

// Originator - this class state we can save as memento
class Game {
    private var level = 0
    private var score = 0

    fun play(levelIncrement: Int, scoreIncrement: Int) {
        level += levelIncrement
        score += scoreIncrement
    }

    fun save(): GameSave {
        println("Saved.")
        return GameSave(level, score)
    }

    fun load(save: GameSave) {
        this.level = save.level
        this.score = save.score
        println("Loaded.")
    }

    fun printStatistics() {
        println("Level: $level. Score: $score.")
    }
}

// Caretaker - this class is responsible for managing mementos
class GameManager {
    private lateinit var saves: Stack<GameSave>
    private lateinit var game: Game

    fun newGame(): Game {
        game = Game()
        saves = Stack()
        println("New game started!")
        return game
    }

    fun makeSave() {
        require(game != null) { "Game must be started."}
        val save = game.save()
        saves.push(save)
    }

    fun loadLastSave() {
        require(game != null) { "Game must be started."}
        val save = saves.pop()
        game.load(save)
    }
}