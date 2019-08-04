package uf.bird.game.controller

import uf.bird.game.model.*

private const val MOVE_SPEED = 120
private const val TUBES_GAP = 200f
private const val FIRST_TUBE_GAP = 350f

class ObstaclesController(private val obstacles: ObstaclesModel,
                          private val bird: BirdModel) {

	init {
		obstacles.tubesPairs.add(TubePair.create(FIRST_TUBE_GAP))
	}

	fun update(deltaTime: Float) {
		moveGround(deltaTime)
		buildTubes(deltaTime)
		countPassedTubes()
		if (obstacleCollideWithBird()) {
			bird.isDead = true
		}
	}

	private fun buildTubes(deltaTime: Float) {
		with(obstacles.tubesPairs) {
			if (peek().tubesX + TUBE_WIDTH < 0) {
				pop()
			}
			if (size < 4) {
				val tubesX = last.tubesX + TUBES_GAP
				add(TubePair.create(tubesX))
			}
			forEach { tubes -> tubes.tubesX -= MOVE_SPEED * deltaTime }
		}
	}

	private fun moveGround(deltaTime: Float) {
		with(obstacles) {
			groundStartX -= MOVE_SPEED * deltaTime
			groundStartX2 -= MOVE_SPEED * deltaTime
			if (groundStartX2 < 0) {
				groundStartX = groundStartX2 + GROUND_WIDTH
			}
			if (groundStartX < 0) {
				groundStartX2 = groundStartX + GROUND_WIDTH
			}
		}
	}

	private fun obstacleCollideWithBird(): Boolean {
		if (bird.hitBox.y <= GROUND_SHOWN_HEIGHT) {
			return true
		}
		obstacles.tubesPairs.forEach {
			if (it.bottomTubeHitbox.overlaps(bird.hitBox) ||
					it.topTubeHitbox.overlaps(bird.hitBox)) {
				return true
			}
		}
		return false
	}

	private fun countPassedTubes() {
		obstacles.tubesPairs.forEach {
			if (!it.passed) {
				if (bird.positionX >= it.tubesX) {
					it.passed = true
					obstacles.tubesPassed++
				}
			}
		}
	}

}