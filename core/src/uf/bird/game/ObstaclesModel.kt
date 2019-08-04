package uf.bird.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import java.util.*
import kotlin.random.Random

//TODO: MIN/MAX_BOTTOM_TUBE_Y count dynamically

private const val TEXTURE_GROUND_PATH = "ground.png"
private const val TEXTURE_TUBE_TOP = "tube_top.png"
private const val TEXTURE_TUBE_BOTTOM = "tube_bottom.png"
private const val TUBES_GAP = 200f
private const val MIN_BOTTOM_TUBE_Y = -220
private const val MAX_BOTTOM_TUBE_Y = 30
private const val TUBE_HEIGHT = 320f

const val TUBE_WIDTH = 52f
const val GROUND_START_Y = -50f
const val GROUND_WIDTH = 336f
const val GROUND_HEIGHT = 112f
const val GROUND_SHOWN_HEIGHT = GROUND_HEIGHT + GROUND_START_Y

class ObstaclesModel {

	val groundTexture = Texture(TEXTURE_GROUND_PATH)
	val topTubeTexture = Texture(TEXTURE_TUBE_TOP)
	val bottomTubeTexture = Texture(TEXTURE_TUBE_BOTTOM)

	val tubesPairs = LinkedList<TubePair>()

	var groundStartX = 0f
	var groundStartX2 = groundStartX + groundTexture.width

}

class TubePair private constructor(
		val bottomTubeY: Float,
		val topTubeY: Float,
		var tubesX: Float) {

	val topTubeHitbox
		get() = Rectangle(tubesX, topTubeY, TUBE_WIDTH, TUBE_HEIGHT)

	val bottomTubeHitbox
		get() = Rectangle(tubesX, bottomTubeY, TUBE_WIDTH, TUBE_HEIGHT)

	companion object {
		fun create(tubesX: Float): TubePair {
			val bottomTubeY = Random.nextInt(MIN_BOTTOM_TUBE_Y, MAX_BOTTOM_TUBE_Y).toFloat()
			val topTubeY = bottomTubeY + TUBE_HEIGHT + TUBES_GAP
			return TubePair(bottomTubeY, topTubeY, tubesX)
		}
	}
}