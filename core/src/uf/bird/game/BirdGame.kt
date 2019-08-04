package uf.bird.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.BitmapFont

private const val BACKGROUND_PATH = "background.png"
private const val FONT_PATH = "gta_font.fnt"
private const val MUSIC_PATH = "gta_soundtrack.mp3"
private const val MUSIC_VOLUME = 0.2f
private const val PREFERENCES_NAME = "UF_BIRD"
private const val PREFERENCES_HIGHEST_SCORE_KEY = "highestScore"

class BirdGame : Game() {

	private lateinit var music: Music

	lateinit var batch: SpriteBatch
	lateinit var background: Texture
	lateinit var font: BitmapFont
	lateinit var prefs: Preferences

	override fun create() {
		batch = SpriteBatch()
		background = Texture(BACKGROUND_PATH)
		font = BitmapFont(Gdx.files.internal(FONT_PATH))
		music = Gdx.audio.newMusic(Gdx.files.internal(MUSIC_PATH))
		music.isLooping = true
		music.volume = MUSIC_VOLUME
		music.play()
		prefs = Gdx.app.getPreferences(PREFERENCES_NAME)
		currentTopScore = prefs.getInteger(PREFERENCES_HIGHEST_SCORE_KEY, 0)
		setScreen(StartScreen(this))

		Gdx.gl.glClearColor(.5f, .5f, .5f, 1f)
	}

	override fun dispose() {
		batch.dispose()
		background.dispose()
		music.dispose()
		font.dispose()
		prefs.putInteger(PREFERENCES_HIGHEST_SCORE_KEY, currentTopScore)
		prefs.flush()
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

		var currentTopScore = 0
	}
}
