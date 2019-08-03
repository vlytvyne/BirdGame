package uf.bird.game

import com.badlogic.gdx.Gdx

class BirdController(private val bird: BirdModel) {

	private val isTouched
		get() = Gdx.input.justTouched()

	private var animationTime = 0f

	fun updateBirdPosition(deltaTime: Float) {
		calculateBirdPosition(deltaTime)
		setAppropriateFrame(deltaTime)
	}

	private fun setAppropriateFrame(deltaTime: Float) {
		animationTime += deltaTime
		bird.currentFrame = bird.animation.getKeyFrame(animationTime, true)
	}

	private fun calculateBirdPosition(deltaTime: Float) {
		if (isTouched) {
			bird.velocityY = JUMP_VELOCITY
		} else {
			bird.velocityY += GRAVITY * deltaTime
		}
		bird.positionY += bird.velocityY
	}
}