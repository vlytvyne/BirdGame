package uf.bird.game

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch

private const val WASTED_PATH = "wasted.png"
private const val WASTED_TEXT_WIDTH = 200f
private const val WASTED_TEXT_HEIGHT = 70f
private const val WASTED_TEXT_TOP_MARGIN = 60f

class WastedScreen(private val game: BirdGame,
                   private val bird: BirdModel,
                   private val obstacles:ObstaclesModel): ScreenAdapter() {

	private lateinit var wasted: Texture

	override fun show() {
		wasted = Texture(WASTED_PATH)
	}

	override fun render(delta: Float) {

		game.batch.use {
			drawBackground(it)
			it.draw(wasted,
					(BirdGame.screenWidth -  WASTED_TEXT_WIDTH) / 2,
					BirdGame.screenHeight - WASTED_TEXT_HEIGHT - WASTED_TEXT_TOP_MARGIN,
					WASTED_TEXT_WIDTH,
					WASTED_TEXT_HEIGHT)
		}
	}

	private fun drawBackground(batch: Batch) {
		batch.draw(game.background, 0f, 0f, BirdGame.screenWidth, BirdGame.screenHeight)
		batch.draw(bird.currentFrame, bird.positionX, bird.positionY, BIRD_WIDTH, BIRD_HEIGHT)
		obstacles.tubesPairs.forEach { tubes ->
			batch.draw(obstacles.bottomTubeTexture, tubes.tubesX, tubes.bottomTubeY)
			batch.draw(obstacles.topTubeTexture, tubes.tubesX, tubes.topTubeY)
		}
		batch.draw(obstacles.groundTexture, obstacles.groundStartX, GROUND_START_Y)
		batch.draw(obstacles.groundTexture, obstacles.groundStartX2, GROUND_START_Y)
	}

	override fun hide() {
		wasted.dispose()
	}
}