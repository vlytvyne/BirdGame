package uf.bird.game

import com.badlogic.gdx.graphics.Texture

const val TEXTURE_GROUND_PATH = "ground.png"
const val GROUND_HEIGHT = -50f

class ObstaclesModel {

	val groundTexture = Texture(TEXTURE_GROUND_PATH)

	val groundTextureWidth = groundTexture.width

	var groundStartX = 0f

	var groundStartX2 = groundStartX + groundTexture.width

}