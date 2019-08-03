package uf.bird.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array as GdxArray

const val TEXTURE_PATH = "birdanimation.png"
const val ANIMATION_FRAMES_COUNT = 3
const val FRAME_DURATION = 0.1f
const val GRAVITY = -20f
const val JUMP_VELOCITY = 7f
const val ONE_SECOND = 1f

class BirdModel(val positionX: Float, var positionY: Float, val width: Float, val height: Float) {

	private val spriteList = Texture(TEXTURE_PATH)

	val animation: Animation<TextureRegion>
	var currentFrame: TextureRegion

	init{
		val tmp2dimensionalFrames = TextureRegion.split(spriteList,
				spriteList.width / ANIMATION_FRAMES_COUNT, spriteList.height)
		val frames = GdxArray(tmp2dimensionalFrames[0])
		animation = Animation(FRAME_DURATION, frames)
		currentFrame = animation.getKeyFrame(0f, true)
	}

	var velocityY = 0f

}