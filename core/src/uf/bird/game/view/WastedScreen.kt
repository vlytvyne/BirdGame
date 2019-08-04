package uf.bird.game.view

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import uf.bird.game.global.*
import uf.bird.game.model.*

private const val WASTED_PATH = "wasted.png"
private const val TRY_AGAIN_PATH = "try_again.png"
private const val WASTED_WIDTH = 200f
private const val WASTED_HEIGHT = 70f
private const val WASTED_TEXT_TOP_MARGIN = 60f
private const val USER_SCORE_TEXT_TOP_MARGIN = 30f
private const val TOP_SCORE_TEXT_TOP_MARGIN = 30f

class WastedScreen(private val game: BirdGame,
                   private val bird: BirdModel,
                   private val obstacles: ObstaclesModel): ScreenAdapter() {

	private lateinit var wasted: Texture
	private lateinit var tryAgain: Texture

	override fun show() {
		wasted = Texture(WASTED_PATH)
		tryAgain = Texture(TRY_AGAIN_PATH)
		game.font.data.setScale(1f)

		if (obstacles.tubesPassed > game.currentTopScore) {
			game.currentTopScore = obstacles.tubesPassed
		}
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
		batch.draw(game.background, 0f, 0f, VIEWPORT_WIDTH, VIEWPORT_HEIGHT)
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
				(VIEWPORT_WIDTH - WASTED_WIDTH) / 2,
				VIEWPORT_HEIGHT - WASTED_HEIGHT - WASTED_TEXT_TOP_MARGIN,
				WASTED_WIDTH,
				WASTED_HEIGHT)
		batch.draw(tryAgain, 0f, GROUND_SHOWN_HEIGHT, VIEWPORT_WIDTH, 300f)
	}


	private fun drawScore(batch: Batch) {
		val userScore = "Your score is: ${obstacles.tubesPassed}"
		val glyphLayout = GlyphLayout()
		glyphLayout.setText(game.font, userScore)
		val textStartX = (VIEWPORT_WIDTH - glyphLayout.width) / 2
		val textStartY = VIEWPORT_HEIGHT - WASTED_HEIGHT - WASTED_TEXT_TOP_MARGIN - USER_SCORE_TEXT_TOP_MARGIN
		game.font.draw(batch, userScore,
				(VIEWPORT_WIDTH - glyphLayout.width) / 2,
				textStartY
		)

		val topScore = "Top score is: ${game.currentTopScore}"
		glyphLayout.setText(game.font, topScore)
		game.font.draw(batch, topScore,
				(VIEWPORT_WIDTH - glyphLayout.width) / 2,
				VIEWPORT_HEIGHT - WASTED_HEIGHT - WASTED_TEXT_TOP_MARGIN - USER_SCORE_TEXT_TOP_MARGIN - TOP_SCORE_TEXT_TOP_MARGIN
				)
	}

	override fun hide() {
		wasted.dispose()
		tryAgain.dispose()
	}

}