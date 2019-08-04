package uf.bird.game.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import uf.bird.game.global.*

private const val PLAY_BTN_PATH = "play_button.png"
private const val PLAY_BTN_WIDTH = 90f
private const val PLAY_BTN_HEIGHT = 55f

class StartScreen(private val game: BirdGame): ScreenAdapter() {

	private lateinit var playButton: Texture
	private lateinit var playButtonHitbox: Rectangle

	override fun show() {
		playButton = Texture(PLAY_BTN_PATH)
		playButtonHitbox = Rectangle(
				(VIEWPORT_WIDTH - PLAY_BTN_WIDTH) / 2,
				(VIEWPORT_HEIGHT - PLAY_BTN_HEIGHT) / 2,
				PLAY_BTN_WIDTH,
				PLAY_BTN_HEIGHT
		)
		game.font.data.setScale(1f)
	}

	override fun render(delta: Float) {
		game.batch.use {
			it.draw(game.background, 0f, 0f, VIEWPORT_WIDTH, VIEWPORT_HEIGHT)
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

	private val isPlayButtonPressed: Boolean
			get() {
				if (isTouched) {
					val touchCoordinate = Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
					//convert screen coordinates to viewport coordinates
					game.camera.unproject(touchCoordinate)
					if (playButtonHitbox.contains(touchCoordinate.x, touchCoordinate.y)) {
						return true
					}
				}
				return false
			}

	override fun hide() {
		playButton.dispose()
	}
}