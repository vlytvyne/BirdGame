package uf.bird.game.global

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import uf.bird.game.view.StartScreen

const val VIEWPORT_WIDTH = 320f
const val VIEWPORT_HEIGHT = 600f
const val VIEWPORT_CENTER_X = VIEWPORT_WIDTH / 2
const val VIEWPORT_CENTER_Y = VIEWPORT_HEIGHT / 2

private const val BACKGROUND_PATH = "background.png"
private const val FONT_PATH = "gta_font.fnt"
private const val MUSIC_PATH = "gta_soundtrack.mp3"
private const val MUSIC_VOLUME = 0.2f
private const val PREFERENCES_NAME = "UF_BIRD"
private const val PREFERENCES_HIGHEST_SCORE_KEY = "highestScore"

class BirdGame : Game() {

	private lateinit var music: Music

	private lateinit var prefs: Preferences

	lateinit var batch: SpriteBatch
	lateinit var background: Texture
	lateinit var font: BitmapFont
	lateinit var camera: OrthographicCamera

	var currentTopScore = 0

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

		camera = OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)
		camera.position.x = VIEWPORT_CENTER_X
		camera.position.y = VIEWPORT_CENTER_Y
		camera.update()

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

	override fun render() {
		batch.projectionMatrix = camera.combined
		super.render()
	}
}
