package uf.bird.game.model

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Array as GdxArray

private const val TEXTURE_PATH = "bird_animation.png"
private const val ANIMATION_FRAMES_COUNT = 3
private const val FRAME_DURATION = 0.1f

const val BIRD_WIDTH = 50f
const val BIRD_HEIGHT = 30f

class BirdModel(val positionX: Float, var positionY: Float) {

	private val spriteList = Texture(TEXTURE_PATH)

	val topOfBird
		get() = positionY + spriteList.height

	val hitBox
		get() = Rectangle(
				positionX,
				positionY,
				(spriteList.width / ANIMATION_FRAMES_COUNT).toFloat(),
				spriteList.height.toFloat()
		)

	val animation: Animation<TextureRegion>

	var velocityY = 0f
	var currentFrame: TextureRegion
	var isDead = false

	init{
		val tmp2dimensionalFrames = TextureRegion.split(spriteList,
				spriteList.width / ANIMATION_FRAMES_COUNT, spriteList.height)
		val frames = GdxArray(tmp2dimensionalFrames[0])
		animation = Animation(FRAME_DURATION, frames)
		currentFrame = animation.getKeyFrame(0f, true)
	}

}