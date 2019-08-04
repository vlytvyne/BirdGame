package uf.bird.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

private const val PLAY_BTN_PATH = "play_button.png"
private const val PLAY_BTN_WIDTH = 90f
private const val PLAY_BTN_HEIGHT = 55f

class StartScreen(private val game: BirdGame): ScreenAdapter() {

	private lateinit var playButton: Texture
	private lateinit var playButtonHitbox: Rectangle

	override fun show() {
		playButton = Texture(PLAY_BTN_PATH)
		playButtonHitbox = Rectangle(
				(BirdGame.screenWidth - PLAY_BTN_WIDTH) / 2,
				(BirdGame.screenHeight - PLAY_BTN_HEIGHT) / 2,
				PLAY_BTN_WIDTH,
				PLAY_BTN_HEIGHT
		)
		game.font.data.setScale(1f)
	}

	override fun render(delta: Float) {
		game.batch.use {
			it.draw(game.background, 0f, 0f, BirdGame.screenWidth, BirdGame.screenHeight)
			it.draw(playButton,
					playButtonHitbox.x,
					playButtonHitbox.y,
					playButtonHitbox.width,
					playButtonHitbox.height)
		}
		if (isPlayButtonPressed) {
			game.setScreen(PlayScreen(game))
		}
	}

	private val isPlayButtonPressed
			get() = playButtonHitbox.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) && isTouched

	override fun hide() {
		playButton.dispose()
	}
}