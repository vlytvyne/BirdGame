package uf.bird.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.BitmapFont

private const val BACKGROUND_PATH = "background.png"
private const val FONT_PATH = "gta_font.fnt"

class BirdGame : Game() {

	lateinit var batch: SpriteBatch
	lateinit var background: Texture
	lateinit var font: BitmapFont

	override fun create() {
		batch = SpriteBatch()
		background = Texture(BACKGROUND_PATH)
		font = BitmapFont(Gdx.files.internal(FONT_PATH))
		setScreen(PlayScreen(this))

		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f)
	}

	override fun dispose() {
		batch.dispose()
	}

	companion object {
		val screenWidth
			get() = Gdx.graphics.width.toFloat()

		val screenHeight
			get() = Gdx.graphics.height.toFloat()

		val deltaTime
			get() = Gdx.graphics.deltaTime

		val screenCenterX
			get() = (screenWidth / 2)

		val screenCenterY
			get() = (screenHeight / 2)
	}
}
