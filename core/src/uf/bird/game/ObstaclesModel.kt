package uf.bird.game

import com.badlogic.gdx.graphics.Texture
import kotlin.random.Random

private const val TEXTURE_GROUND_PATH = "ground.png"
private const val TEXTURE_TUBE_TOP = "tube_top.png"
private const val TEXTURE_TUBE_BOTTOM = "tube_bottom.png"
private const val TUBES_GAP = 120

private const val MIN_BOTTOM_TUBE_Y = -200
private const val MAX_BOTTOM_TUBE_Y = 30

const val GROUND_START_Y = -50f

class ObstaclesModel {

	val groundTexture = Texture(TEXTURE_GROUND_PATH)
	val topTubeTexture = Texture(TEXTURE_TUBE_TOP)
	val bottomTubeTexture = Texture(TEXTURE_TUBE_BOTTOM)

	private val tubeHeight = bottomTubeTexture.height.toFloat()

	val tubes = TubePair.create(tubeHeight)

	val groundTextureWidth = groundTexture.width
	var groundStartX = 0f
	var groundStartX2 = groundStartX + groundTexture.width

}

class TubePair private constructor(
		val bottomTubeY: Float,
		val topTubeY: Float,
		var tubesX: Float) {

	companion object {
		fun create(tubeHeight: Float): TubePair {
			val bottomTubeY = Random.nextInt(MIN_BOTTOM_TUBE_Y, MAX_BOTTOM_TUBE_Y).toFloat()
			val topTubeY = bottomTubeY + tubeHeight + TUBES_GAP
			return TubePair(bottomTubeY, topTubeY, 350f)
		}
	}
}