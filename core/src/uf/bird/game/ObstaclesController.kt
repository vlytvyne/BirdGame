package uf.bird.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Rectangle

private const val MOVE_SPEED = 120
private const val TUBES_GAP = 200f
private const val FIRST_TUBE_GAP = 350f

class ObstaclesController(private val obstacles: ObstaclesModel): CollisionDetector {

	init {
		obstacles.tubesPairs.add(TubePair.create(FIRST_TUBE_GAP))
	}

	fun update(deltaTime: Float) {
		moveGround(deltaTime)
		buildTubes(deltaTime)
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

	override fun canCollide(entity: Rectangle): Boolean {
		if (entity.y <= GROUND_SHOWN_HEIGHT) {
			return true
		}
		obstacles.tubesPairs.forEach {
			if (it.bottomTubeHitbox.overlaps(entity) ||
					it.topTubeHitbox.overlaps(entity)) {
				return true
			}
		}
		return false
	}
}

interface CollisionDetector {

	fun canCollide(entity: Rectangle): Boolean
}