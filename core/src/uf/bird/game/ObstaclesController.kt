package uf.bird.game

private const val MOVE_SPEED = 3

class ObstaclesController(private val obstacles: ObstaclesModel) {

	fun update(deltaTime: Float) {
		with(obstacles) {
			groundStartX -= MOVE_SPEED
			groundStartX2 -= MOVE_SPEED
			if (groundStartX2 < 0) {
				groundStartX = groundStartX2 + groundTextureWidth
			}
			if (groundStartX < 0) {
				groundStartX2 = groundStartX + groundTextureWidth
			}
		}
	}
}