package uf.bird.game

private const val MOVE_SPEED = 120

class ObstaclesController(private val obstacles: ObstaclesModel) {

	fun update(deltaTime: Float) {
		moveGround(deltaTime)

		obstacles.tubes.tubesX -= MOVE_SPEED * deltaTime
	}

	private fun moveGround(deltaTime: Float) {
		with(obstacles) {
			groundStartX -= MOVE_SPEED * deltaTime
			groundStartX2 -= MOVE_SPEED * deltaTime
			if (groundStartX2 < 0) {
				groundStartX = groundStartX2 + groundTextureWidth
			}
			if (groundStartX < 0) {
				groundStartX2 = groundStartX + groundTextureWidth
			}
		}
	}
}