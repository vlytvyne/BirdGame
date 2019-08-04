package uf.bird.game.controller

import com.badlogic.gdx.Gdx
import uf.bird.game.global.VIEWPORT_HEIGHT
import uf.bird.game.model.BirdModel

private const val TOP_OFFSET = 50
private const val GRAVITY = -20f
private const val JUMP_VELOCITY = 7f

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
		if (isTouched && bird.topOfBird < VIEWPORT_HEIGHT - TOP_OFFSET) {
			bird.velocityY = JUMP_VELOCITY
		} else {
			bird.velocityY += GRAVITY * deltaTime
		}
		bird.positionY += bird.velocityY
	}
}