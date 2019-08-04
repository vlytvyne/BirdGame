package uf.bird.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.GlyphLayout


private const val WASTED_PATH = "wasted.png"
private const val WASTED_WIDTH = 200f
private const val WASTED_HEIGHT = 70f
private const val WASTED_TEXT_TOP_MARGIN = 60f
private const val SCORE_TEXT_TOP_MARGIN = 30f

private const val TRY_AGAIN_PATH = "try_again.png"

class WastedScreen(private val game: BirdGame,
                   private val bird: BirdModel,
                   private val obstacles:ObstaclesModel): ScreenAdapter() {

	private lateinit var wasted: Texture
	private lateinit var tryAgain: Texture

	override fun show() {
		wasted = Texture(WASTED_PATH)
		tryAgain = Texture(TRY_AGAIN_PATH)
		game.font.data.setScale(1f)
	}

	override fun render(delta: Float) {

		game.batch.use {
			drawBackground(it)
			drawGraphics(it)
			drawScore(it)
		}
		if (isTouched) {
			game.setScreen(PlayScreen(game))
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

	private fun drawGraphics(batch: Batch) {
		batch.draw(wasted,
				(BirdGame.screenWidth - WASTED_WIDTH) / 2,
				BirdGame.screenHeight - WASTED_HEIGHT - WASTED_TEXT_TOP_MARGIN,
				WASTED_WIDTH,
				WASTED_HEIGHT)
		batch.draw(tryAgain, 0f, GROUND_SHOWN_HEIGHT, BirdGame.screenWidth, 300f)
	}


	private fun drawScore(batch: Batch) {
		val text = "Your score is: ${obstacles.tubesPassed}"
		val glyphLayout = GlyphLayout()
		glyphLayout.setText(game.font, text)
		game.font.draw(batch, text,
				(BirdGame.screenWidth - glyphLayout.width) / 2,
				BirdGame.screenHeight - WASTED_HEIGHT - WASTED_TEXT_TOP_MARGIN - SCORE_TEXT_TOP_MARGIN)
	}

	override fun hide() {
		wasted.dispose()
		tryAgain.dispose()
	}
}