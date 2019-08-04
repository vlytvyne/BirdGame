package uf.bird.game

import com.badlogic.gdx.Gdx

private const val TOP_OFFSET = 50
private const val GRAVITY = -20f
private const val JUMP_VELOCITY = 7f

class BirdController(private val bird: BirdModel, private val collisionDetector: CollisionDetector) {

	private val isTouched
		get() = Gdx.input.justTouched()

	private var animationTime = 0f

	fun updateBirdPosition(deltaTime: Float) {
		calculateBirdPosition(deltaTime)
		setAppropriateFrame(deltaTime)
		checkCollision()
	}

	private fun checkCollision() {
		if (collisionDetector.canCollide(bird.hitBox)) {
			Gdx.app.log("TAG", "COLLISION")
		}
	}

	private fun setAppropriateFrame(deltaTime: Float) {
		animationTime += deltaTime
		bird.currentFrame = bird.animation.getKeyFrame(animationTime, true)
	}

	private fun calculateBirdPosition(deltaTime: Float) {
		if (isTouched && bird.topOfBird < BirdGame.screenHeight - TOP_OFFSET) {
			bird.velocityY = JUMP_VELOCITY
		} else {
			bird.velocityY += GRAVITY * deltaTime
		}
		bird.positionY += bird.velocityY
	}
}